/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.annotation.AnnotationDefineException;
import com.github.nest.arcteryx.meta.annotation.ArcteryxResourceAware;
import com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanProperty;
import com.github.nest.arcteryx.meta.beans.annotation.ConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.annotation.DefaultValue;
import com.github.nest.arcteryx.meta.beans.internal.BeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.util.AnnotationUtils;

/**
 * bean property descriptor generator
 * 
 * @author brad.wu
 */
public class BeanPropertyDescriptorGenerator implements IResourcePropertyDescriptorGenerator {
	private ArcteryxResourceAware aware = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator#setAware(com.github.nest.arcteryx.meta.annotation.ArcteryxResourceAware)
	 */
	@Override
	public void setAware(ArcteryxResourceAware aware) {
		this.aware = aware;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator#getAware()
	 */
	@Override
	public ArcteryxResourceAware getAware() {
		return this.aware;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator#createDescriptor(com.github.nest.arcteryx.meta.annotation.IResourcePropertyDescriptorGenerator.IPropertyDeclaration,
	 *      java.lang.annotation.Annotation)
	 */
	@Override
	public IPropertyDescriptor createDescriptor(IPropertyDeclaration property, Annotation annotation) throws Exception {
		BeanPropertyDescriptor descriptor = (BeanPropertyDescriptor) createPropertyDescriptor(property, annotation);

		// constraints
		readConstraints(property, descriptor);
		// default value
		readDefaultValue(property, descriptor);
		return descriptor;
	}

	/**
	 * read default value
	 * 
	 * @param property
	 * @param descriptor
	 */
	protected void readDefaultValue(IPropertyDeclaration property, BeanPropertyDescriptor descriptor) {
		Annotation[] annotations = AnnotationUtils.getAnnotations(property.getFields(), property.getMethods(),
				DefaultValue.class);
		if (annotations != null) {
			if (annotations.length > 1) {
				throw new AnnotationDefineException("Default value @ property [" + property.getPropertyName() + "@"
						+ property.getDeclaringClass().getName() + "] defines repeated, only one is allowed.");
			} else if (annotations.length == 1) {
				DefaultValue defaultValue = (DefaultValue) annotations[0];
				descriptor.setDefaultValue(defaultValue.value());
				descriptor.setDefaultValueFormat(defaultValue.format());
			}
		}
	}

	/**
	 * read constraints
	 * 
	 * @param property
	 * @param descriptor
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected void readConstraints(IPropertyDeclaration property, BeanPropertyDescriptor descriptor) throws Exception {
		Annotation[] annotations = AnnotationUtils.getAnnotations(property.getFields(), property.getMethods());
		List<IConstraint> constraints = ConstraintUtils.generateConstraints(annotations);
		if (constraints != null && constraints.size() != 0) {
			descriptor.setConstraints(this.convertToPropertyConstraints(constraints));
		}
		// constraint reorganizer
		readConstraintReorganizer(property, descriptor);
	}

	/**
	 * convert to bean constraint list
	 * 
	 * @param constraints
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected List<IBeanPropertyConstraint> convertToPropertyConstraints(List<IConstraint> constraints) {
		List<IBeanPropertyConstraint> list = new ArrayList<IBeanPropertyConstraint>(constraints.size());
		for (IConstraint constraint : constraints) {
			list.add((IBeanPropertyConstraint) constraint);
		}
		return list;
	}

	/**
	 * read constraint reorganizer
	 * 
	 * @param property
	 * @param descriptor
	 * @throws Exception
	 */
	protected void readConstraintReorganizer(IPropertyDeclaration property, BeanPropertyDescriptor descriptor)
			throws Exception {
		Annotation[] annotations = AnnotationUtils.getAnnotations(property.getFields(), property.getMethods(),
				ConstraintReorganizer.class);
		if (annotations != null) {
			if (annotations.length > 1) {
				throw new AnnotationDefineException("Constraint reogranizer @ property [" + property.getPropertyName()
						+ "@" + property.getDeclaringClass().getName() + "] defines repeated, only one is allowed.");
			} else if (annotations.length == 1) {
				IConstraintReorganizerGenerator generator = annotations[0].annotationType()
						.getAnnotation(ConstraintReorganizer.class).generator().newInstance();
				descriptor.setConstraintReorganizer((IBeanPropertyConstraintReorganizer) generator
						.generate(annotations[0]));
			}
		}
	}

	/**
	 * create property descriptor
	 * 
	 * @param property
	 * @param annotation
	 * @return
	 */
	protected BeanPropertyDescriptor createPropertyDescriptor(IPropertyDeclaration property, Annotation annotation) {
		ArcteryxBeanProperty propertyAnnotation = (ArcteryxBeanProperty) annotation;
		BeanPropertyDescriptor descriptor = new BeanPropertyDescriptor();
		descriptor.setName(property.getPropertyName());
		descriptor.setDescription(propertyAnnotation.description());
		return descriptor;
	}
}
