/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.oval.Check;
import net.sf.oval.configuration.pojo.elements.ClassConfiguration;
import net.sf.oval.configuration.pojo.elements.FieldConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodReturnValueConfiguration;
import net.sf.oval.internal.util.ReflectionUtils;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IValidationConfigurationInitializer;
import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;
import com.github.nest.arcteryx.meta.beans.internal.validators.ValidationConfiguration;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.DateRangeConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.NotNullConvertor;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.TextFormatConvertor;

/**
 * OVal configurer initializer
 * 
 * @author brad.wu
 */
public class OValValidationConfigurationInitializer implements IValidationConfigurationInitializer {
	/**
	 * when initialize the configuration, use getter method first or not
	 */
	private boolean getterFirst = true;
	/**
	 * OVal validation configurer
	 */
	private IOValConfigurer configurer = null;

	private Map<Class<?>, IOValCheckConvertor<?>> convertors = new HashMap<Class<?>, IOValCheckConvertor<?>>();

	public OValValidationConfigurationInitializer() {
		this.configurer = createConfigurer();
		this.setConvertors(createDefaultConvertors());
	}

	/**
	 * create default convertors
	 * 
	 * @return
	 */
	protected Collection<IOValCheckConvertor<?>> createDefaultConvertors() {
		List<IOValCheckConvertor<?>> convertors = new LinkedList<IOValCheckConvertor<?>>();
		convertors.add(new NotNullConvertor());
		convertors.add(new DateRangeConvertor());
		convertors.add(new TextFormatConvertor());
		return convertors;
	}

	/**
	 * create OVal configurer
	 * 
	 * @return
	 */
	protected IOValConfigurer createConfigurer() {
		return new OValConfigurer();
	}

	/**
	 * 
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
	 * @return the convertors
	 */
	public Collection<IOValCheckConvertor<?>> getConvertors() {
		return convertors.values();
	}

	/**
	 * @param convertors
	 *            the convertors to set
	 */
	@SuppressWarnings("rawtypes")
	public void setConvertors(Collection<IOValCheckConvertor<?>> convertors) {
		assert convertors != null : "Convertor of constraints cannot be null.";

		for (IOValCheckConvertor convertor : convertors) {
			this.convertors.put(convertor.getSupportedConstraintType(), convertor);
		}
	}

	/**
	 * @return the configurer
	 */
	public IOValConfigurer getConfigurer() {
		return configurer;
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
		return new ValidationConfiguration(getConfigurer());
	}

	/**
	 * initialize validation configuration of given descriptor
	 * 
	 * @param descriptor
	 */
	protected void initialize(IBeanDescriptor descriptor) {
		for (IBeanPropertyDescriptor property : descriptor.getBeanProperties()) {
			if (property.getBeanDescriptor() != descriptor) {
				continue;
			}
			ClassConfiguration config = buildPropertyConstraint(property);
			if (hasConstraint(config)) {
				this.getConfigurer().addClassConfiguration(config);
			}
		}
	}

	/**
	 * check the configuration has constriant or not
	 * 
	 * @param config
	 * @return
	 */
	protected boolean hasConstraint(ClassConfiguration config) {
		return (config.fieldConfigurations != null && config.fieldConfigurations.size() != 0)
				|| (config.methodConfigurations != null && config.methodConfigurations.size() != 0)
				|| (config.constructorConfigurations != null && config.constructorConfigurations.size() != 0)
				|| (config.objectConfiguration != null);
	}

	/**
	 * build constraint for property
	 * 
	 * @param property
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ClassConfiguration buildPropertyConstraint(IBeanPropertyDescriptor property) {
		IBeanDescriptor bean = property.getBeanDescriptor();
		Class<?> beanClass = bean.getBeanClass();

		// initialize field and method configurations. method configurations
		// only will be used when getterFirst is true.
		ClassConfiguration configuration = new ClassConfiguration();
		configuration.type = beanClass;
		configuration.fieldConfigurations = new HashSet<FieldConfiguration>();
		if (this.isGetterFirst()) {
			configuration.methodConfigurations = new HashSet<MethodConfiguration>();
		}

		// get property name
		String propertyName = property.getName();

		List<Check> checkList = new LinkedList<Check>();
		// get constraint recursive. exclude the constraint collection object
		List<IBeanPropertyConstraint> constraints = property.getConstraint().getConstraintsRecursive();
		for (IBeanPropertyConstraint constraint : constraints) {
			// convert constraint to OVal check
			IOValCheckConvertor convertor = convertors.get(constraint.getClass());
			if (convertor == null) {
				throw new BeanValidationException("Convertor of [" + constraint + "] not found.");
			}

			Check check = convertor.convert(constraint);
			checkList.add(check);
		}

		MethodConfiguration mc = null;
		if (this.isGetterFirst()) {
			Method method = ReflectionUtils.getGetterRecursive(beanClass, propertyName);
			if (method != null) {
				mc = new MethodConfiguration();
				mc.name = method.getName();
				mc.isInvariant = Boolean.TRUE;
				MethodReturnValueConfiguration mrvc = new MethodReturnValueConfiguration();
				mrvc.checks = checkList;
				mc.returnValueConfiguration = mrvc;
				configuration.methodConfigurations.add(mc);
			}
		}
		if (mc == null) {
			FieldConfiguration fc = new FieldConfiguration();
			fc.name = propertyName;
			fc.checks = checkList;
			configuration.fieldConfigurations.add(fc);
		}
		return configuration;
	}
}
