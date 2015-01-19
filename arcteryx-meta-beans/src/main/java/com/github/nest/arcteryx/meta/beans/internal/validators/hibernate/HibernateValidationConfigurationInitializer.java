/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.lang.annotation.ElementType;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Validation;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.PropertyConstraintMappingContext;
import org.hibernate.validator.cfg.context.TypeConstraintMappingContext;

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
 * hibernate validation configuration initializer
 * 
 * @author brad.wu
 */
public class HibernateValidationConfigurationInitializer implements IValidationConfigurationInitializer {
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
		return Validation.byProvider(HibernateValidator.class).configure();
	}

	/**
	 * initialize constraints of bean descriptor
	 * 
	 * @param descriptor
	 */
	@SuppressWarnings("rawtypes")
	protected void initialize(IBeanDescriptor descriptor) {
		ConstraintMapping mapping = getConfiguration().createConstraintMapping();
		TypeConstraintMappingContext context = mapping.type(descriptor.getBeanClass());

		this.generateTypeLevelConstraints(descriptor, context);
		this.generatePropertyLevelConstraints(descriptor, context);
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
	protected void generatePropertyLevelConstraints(IBeanDescriptor descriptor, TypeConstraintMappingContext context) {
		for (IBeanPropertyDescriptor property : descriptor.getBeanProperties()) {
			IBeanPropertyConstraintReorganizer reorganizer = property.getConstraintReorganizer();
			if (reorganizer == null) {
				reorganizer = getPropertyConstraintReorganizer();
			}
			List<IBeanPropertyConstraint> constraints = reorganizer.getEffectiveConstraints(property);
			List<ConstraintDef> defines = this.convertToHibernateConstraintDefines(constraints);

			String propertyName = property.getName();
			PropertyConstraintMappingContext propertyContext = context.property(propertyName, ElementType.FIELD);
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
}
