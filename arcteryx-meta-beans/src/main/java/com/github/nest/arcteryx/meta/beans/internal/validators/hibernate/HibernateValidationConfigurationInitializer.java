/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Validation;
import javax.validation.ValidationProviderResolver;
import javax.validation.spi.ValidationProvider;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.PropertyConstraintMappingContext;
import org.hibernate.validator.cfg.context.TypeConstraintMappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.IValidationConfigurationInitializer;
import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanPropertyConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;
import com.github.nest.arcteryx.meta.beans.internal.validators.ValidationConfiguration;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.AssertValidDef;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.AssertValidConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.BeanScriptConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.DateRangeConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.EmailConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.LengthConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.NotBlankConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.NotEmptyConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.NotNegativeConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.NotNullConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.NumberFormatConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.PropertyScriptConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.SizeConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.TextFormatConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors.TheNumberConvertor;

/**
 * hibernate validation configuration initializer.<br>
 * Note: if the {@linkplain #isIgnoreClassHierarchy()} is true, use the
 * customized {@linkplain HibernateValidator513} to ignore the class hierarchy;
 * otherwise use the default {@linkplain HibernateValidator}. Default value is
 * true. The customized HibernateValidator only supports 5.1.3.Final.
 * 
 * @author brad.wu
 */
public class HibernateValidationConfigurationInitializer implements IValidationConfigurationInitializer {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * when initialize the configuration, use getter method first or not
	 */
	private boolean getterFirst = true;
	private boolean ignoreClassHierarchy = true;
	private HibernateValidatorConfiguration configuration = null;
	private IBeanConstraintReorganizer beanConstraintReorganizer = null;
	private IBeanPropertyConstraintReorganizer propertyConstraintReorganizer = null;
	private Map<Class<?>, IHibernateConstraintConvertor<?>> convertors = new HashMap<Class<?>, IHibernateConstraintConvertor<?>>();

	public HibernateValidationConfigurationInitializer() {
		this.configuration = createConfiguration();
		this.setConvertors(createDefaultConvertors());
	}

	/**
	 * create default convertors
	 * 
	 * @return
	 */
	protected Collection<IHibernateConstraintConvertor<?>> createDefaultConvertors() {
		List<IHibernateConstraintConvertor<?>> convertors = new LinkedList<IHibernateConstraintConvertor<?>>();

		convertors.add(new AssertValidConvertor());
		convertors.add(new DateRangeConvertor());
		convertors.add(new EmailConvertor());
		convertors.add(new LengthConvertor());
		convertors.add(new NotBlankConvertor());
		convertors.add(new NotEmptyConvertor());
		convertors.add(new NotNegativeConvertor());
		convertors.add(new NotNullConvertor());
		convertors.add(new TheNumberConvertor());
		convertors.add(new NumberFormatConvertor());
		convertors.add(new SizeConvertor());
		// PropertyScript doesn't support.
		convertors.add(new PropertyScriptConvertor());
		convertors.add(new TextFormatConvertor());

		convertors.add(new BeanScriptConvertor());

		return convertors;
	}

	/**
	 * @return the convertors
	 */
	public Collection<IHibernateConstraintConvertor<?>> getConvertors() {
		return convertors.values();
	}

	/**
	 * @param convertors
	 *            the convertors to set
	 */
	@SuppressWarnings("rawtypes")
	public void setConvertors(Collection<IHibernateConstraintConvertor<?>> convertors) {
		assert convertors != null : "Convertor of constraints cannot be null.";

		for (IHibernateConstraintConvertor convertor : convertors) {
			this.convertors.put(convertor.getSupportedConstraintType(), convertor);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IValidationConfigurationInitializer#initialize(com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext)
	 */
	@Override
	public IValidationConfiguration initialize(IBeanDescriptorContext context) {
		for (IBeanDescriptor descriptor : context.getDescriptors(IBeanDescriptor.class)) {
			initialize(descriptor);
		}
		return new ValidationConfiguration(getConfiguration());
	}

	/**
	 * get hibernate configuration
	 * 
	 * @return
	 */
	public HibernateValidatorConfiguration getConfiguration() {
		return this.configuration;
	}

	/**
	 * create hibernate validation configuration
	 * 
	 * @return
	 */
	protected HibernateValidatorConfiguration createConfiguration() {
		if (this.isIgnoreClassHierarchy()) {
			ValidationProviderResolver resolver = new ValidationProviderResolver() {
				/**
				 * (non-Javadoc)
				 * 
				 * @see javax.validation.ValidationProviderResolver#getValidationProviders()
				 */
				@Override
				public List<ValidationProvider<?>> getValidationProviders() {
					List<ValidationProvider<?>> providers = new ArrayList<ValidationProvider<?>>(1);
					providers.add(new HibernateValidator513());
					return providers;
				}
			};
			return Validation.byProvider(HibernateValidator513.class).providerResolver(resolver).configure();
		} else {
			return Validation.byProvider(HibernateValidator.class).configure();
		}
	}

	/**
	 * initialize constraints of bean descriptor
	 * 
	 * @param descriptor
	 */
	@SuppressWarnings("rawtypes")
	protected void initialize(IBeanDescriptor descriptor) {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialize bean descriptor [" + descriptor + "].");
		}
		ConstraintMapping mapping = getConfiguration().createConstraintMapping();
		TypeConstraintMappingContext context = mapping.type(descriptor.getBeanClass());

		this.generateTypeLevelConstraints(descriptor, context);
		this.generatePropertyLevelConstraints(descriptor, context);

		getConfiguration().addMapping(mapping);
	}

	/**
	 * generate type level constraints
	 * 
	 * @param descriptor
	 * @param context
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void generateTypeLevelConstraints(IBeanDescriptor descriptor, TypeConstraintMappingContext context) {
		// find constraints of parents
		IBeanConstraintReorganizer reorganizer = descriptor.getConstraintReorganizer();
		if (reorganizer == null) {
			reorganizer = getBeanConstraintReorganizer();
		}
		List<IBeanConstraint> list = reorganizer.getEffectiveConstraints(descriptor);
		List<ConstraintDef> defines = this.convertToHibernateConstraintDefines(list);
		for (ConstraintDef define : defines) {
			context.constraint(define);
		}
	}

	/**
	 * generate property level constraints
	 * 
	 * @param property
	 * @param context
	 */
	@SuppressWarnings({ "rawtypes" })
	protected void generatePropertyLevelConstraints(IBeanDescriptor descriptor,
			final TypeConstraintMappingContext context) {
		for (IBeanPropertyDescriptor property : descriptor.getBeanProperties()) {
			IBeanPropertyConstraintReorganizer reorganizer = property.getConstraintReorganizer();
			if (reorganizer == null) {
				reorganizer = getPropertyConstraintReorganizer();
			}
			List<IBeanPropertyConstraint> constraints = reorganizer.getEffectiveConstraints(property);
			List<ConstraintDef> defines = this.convertToHibernateConstraintDefines(constraints);

			PropertyConstraintMappingContext propertyContext = null;
			String propertyName = property.getName();
			if (this.isGetterFirst()) {
				Method method = this.getGetterRecursive(descriptor.getBeanClass(), propertyName);
				if (method == null) {
					propertyContext = context.property(propertyName, ElementType.FIELD);
				} else {
					propertyContext = context.property(propertyName, ElementType.METHOD);
				}
			} else {
				Field field = this.getFieldRecursive(descriptor.getBeanClass(), propertyName);
				if (field == null) {
					propertyContext = context.property(propertyName, ElementType.METHOD);
				} else {
					propertyContext = context.property(propertyName, ElementType.FIELD);
				}
			}

			for (ConstraintDef define : defines) {
				if (define instanceof AssertValidDef) {
					propertyContext.valid();
				} else {
					propertyContext.constraint(define);
				}
			}
		}
	}

	/**
	 * get default bean constraint reorganizer
	 * 
	 * @return
	 */
	protected IBeanConstraintReorganizer getBeanConstraintReorganizer() {
		if (this.beanConstraintReorganizer == null) {
			synchronized (this) {
				if (this.beanConstraintReorganizer == null) {
					this.beanConstraintReorganizer = createDefaultBeanConstraintReorganizer();
				}
			}
		}
		return this.beanConstraintReorganizer;
	}

	/**
	 * create default bean constraint reorganizer
	 * 
	 * @return
	 */
	protected IBeanConstraintReorganizer createDefaultBeanConstraintReorganizer() {
		return new BeanConstraintReorganizer() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see com.github.nest.arcteryx.meta.beans.internal.validators.AbstractConstraintReorganizer#getEffectiveConstraints(com.github.nest.arcteryx.meta.beans.IConstraintContainer)
			 */
			@Override
			public List<IBeanConstraint> getEffectiveConstraints(IBeanDescriptor descriptor) {
				return this.getAllConstraints(descriptor);
			}
		};
	}

	/**
	 * get property constraint reorganizer
	 * 
	 * @return
	 */
	protected IBeanPropertyConstraintReorganizer getPropertyConstraintReorganizer() {
		if (this.propertyConstraintReorganizer == null) {
			synchronized (this) {
				if (this.propertyConstraintReorganizer == null) {
					this.propertyConstraintReorganizer = createDefaultPropertyConstraintReorganizer();
				}
			}
		}
		return this.propertyConstraintReorganizer;
	}

	/**
	 * create default property constraint reorganizer
	 * 
	 * @return
	 */
	protected IBeanPropertyConstraintReorganizer createDefaultPropertyConstraintReorganizer() {
		return new BeanPropertyConstraintReorganizer() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see com.github.nest.arcteryx.meta.beans.internal.validators.AbstractConstraintReorganizer#getEffectiveConstraints(com.github.nest.arcteryx.meta.beans.IConstraintContainer)
			 */
			@Override
			public List<IBeanPropertyConstraint> getEffectiveConstraints(IBeanPropertyDescriptor descriptor) {
				return this.getAllConstraints(descriptor);
			}
		};
	}

	/**
	 * convert to OVal checks
	 * 
	 * @param constraints
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<ConstraintDef> convertToHibernateConstraintDefines(List<?> constraints) {
		List<ConstraintDef> checkList = new LinkedList<ConstraintDef>();
		for (int index = 0, count = constraints.size(); index < count; index++) {
			IConstraint constraint = (IConstraint) constraints.get(index);
			// convert constraint to OVal check
			IHibernateConstraintConvertor convertor = convertors.get(constraint.getClass());
			if (convertor == null) {
				throw new BeanValidationException("Convertor of [" + constraint + "] not found.");
			}

			ConstraintDef check = convertor.convert(constraint);
			checkList.add(check);
		}
		return checkList;
	}

	/**
	 * @return the getterFirst
	 */
	public boolean isGetterFirst() {
		return getterFirst;
	}

	/**
	 * @param getterFirst
	 *            the getterFirst to set
	 */
	public void setGetterFirst(boolean getterFirst) {
		this.getterFirst = getterFirst;
	}

	/**
	 * @return the ignoreClassHierarchy
	 */
	public boolean isIgnoreClassHierarchy() {
		return ignoreClassHierarchy;
	}

	/**
	 * @param ignoreClassHierarchy
	 *            the ignoreClassHierarchy to set
	 */
	public void setIgnoreClassHierarchy(boolean ignoreClassHierarchy) {
		this.ignoreClassHierarchy = ignoreClassHierarchy;
	}

	protected Method getGetterRecursive(final Class<?> clazz, final String propertyName) {
		final Method m = getGetter(clazz, propertyName);
		if (m != null)
			return m;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getGetterRecursive(superclazz, propertyName);
	}

	protected Method getGetter(final Class<?> clazz, final String propertyName) {
		final String appendix = propertyName.substring(0, 1).toUpperCase(Locale.getDefault())
				+ propertyName.substring(1);
		try {
			return clazz.getDeclaredMethod("get" + appendix);
		} catch (final NoSuchMethodException ex) {
			logger.trace("getXXX method not found.", ex);
		}
		try {
			return clazz.getDeclaredMethod("is" + appendix);
		} catch (final NoSuchMethodException ex) {
			logger.trace("isXXX method not found.", ex);
			return null;
		}
	}

	protected Field getFieldRecursive(final Class<?> clazz, final String fieldName) {
		final Field f = getField(clazz, fieldName);
		if (f != null)
			return f;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getFieldRecursive(superclazz, fieldName);
	}

	protected Field getField(final Class<?> clazz, final String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (final NoSuchFieldException e) {
			return null;
		}
	}
}
