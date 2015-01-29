/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration;
import com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.PropertyDeclaration;
import com.github.nest.arcteryx.meta.util.ReflectionUtils;

/**
 * abstract resource descriptor generator
 * 
 * @author brad.wu
 */
public abstract class AbstractResourceDescriptorGenerator implements IResourceDescriptorGenerator {
	private ArcteryxResourceAware aware = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IResourceDescriptorGenerator#setAware(com.github.nest.arcteryx.meta.annotation.ArcteryxResourceAware)
	 */
	@Override
	public void setAware(ArcteryxResourceAware aware) {
		this.aware = aware;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IResourceDescriptorGenerator#getAware()
	 */
	@Override
	public ArcteryxResourceAware getAware() {
		return this.aware;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IResourceDescriptorGenerator#createDescriptor(java.lang.Class)
	 */
	@Override
	public IResourceDescriptor createDescriptor(Class<?> resourceClass) throws Exception {
		IResourceDescriptor descriptor = createDescriptor(getAnnotationDelcareBean(resourceClass), resourceClass);
		readTypeLevelConfiguration(descriptor, resourceClass);
		setProperties(descriptor, this.readProperties(resourceClass, descriptor));
		return descriptor;
	}

	/**
	 * get annotation which declare the bean
	 * 
	 * @param beanClass
	 * @return
	 */
	protected abstract Annotation getAnnotationDelcareBean(Class<?> beanClass);

	/**
	 * create descriptor
	 * 
	 * @param annotation
	 * @param resourceClass
	 * @return
	 */
	protected abstract IResourceDescriptor createDescriptor(Annotation annotation, Class<?> resourceClass);

	/**
	 * default do nothing
	 * 
	 * @param descriptor
	 * @param resourceClass
	 * @throws Exception
	 */
	protected void readTypeLevelConfiguration(IResourceDescriptor descriptor, Class<?> resourceClass) throws Exception {
	}

	/**
	 * read properties
	 * 
	 * @param beanClass
	 * @param descriptor
	 * @throws Exception
	 */
	protected List<IPropertyDescriptor> readProperties(Class<?> beanClass, IResourceDescriptor descriptor)
			throws Exception {
		Map<String, IPropertyDeclaration> properties = new HashMap<String, IPropertyDeclaration>();
		// scan fields
		for (Field field : beanClass.getDeclaredFields()) {
			String propertyName = field.getName();
			IPropertyDeclaration property = new PropertyDeclaration();
			property.addField(field);
			properties.put(propertyName, property);
		}
		// scan methods
		for (Method method : beanClass.getDeclaredMethods()) {
			String propertyName = ReflectionUtils.getPropertyName(method, true);
			if (propertyName != null) {
				IPropertyDeclaration property = properties.get(propertyName);
				if (property == null) {
					property = new PropertyDeclaration();
				}
				property.addMethod(method);
				properties.put(propertyName, property);
			}
		}

		List<IPropertyDescriptor> list = new ArrayList<IPropertyDescriptor>();
		for (IPropertyDeclaration property : properties.values()) {
			if (property.isProperty()) {
				Annotation annotation = property.getDeclaredAnnotation();
				ArcteryxResourceProperty propertyAnnotation = annotation.annotationType().getAnnotation(
						ArcteryxResourceProperty.class);
				IResourcePropertyDescriptorGenerator generator = propertyAnnotation.generator().newInstance();
				generator.setAware(this.getAware());
				IPropertyDescriptor propertyDescriptor = generator.createDescriptor(property, annotation);
				list.add(propertyDescriptor);
			}
		}
		return list;
	}

	/**
	 * set properties
	 * 
	 * @param descriptor
	 * @param properties
	 */
	protected abstract void setProperties(IResourceDescriptor descriptor, List<IPropertyDescriptor> properties);
}
