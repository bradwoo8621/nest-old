/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.annotation.AbstractResourceDescriptorGenerator;
import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBean;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;

/**
 * bean descriptor generator
 * 
 * @author brad.wu
 */
public class BeanDescriptorGenerator extends AbstractResourceDescriptorGenerator {
	/**
	 * create descriptor by given annotation and bean class
	 * 
	 * @param annotation
	 * @param resourceClass
	 * @return
	 */
	protected IResourceDescriptor createDescriptor(Annotation annotation, Class<?> resourceClass) {
		ArcteryxBean bean = (ArcteryxBean) annotation;
		BeanDescriptor descriptor = new BeanDescriptor();
		descriptor.setName(bean.name());
		descriptor.setBeanClass(resourceClass);
		descriptor.setDescription(bean.description());
		return descriptor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.AbstractResourceDescriptorGenerator#getAnnotationDelcareBean(java.lang.Class)
	 */
	@Override
	protected Annotation getAnnotationDelcareBean(Class<?> beanClass) {
		return beanClass.getAnnotation(ArcteryxBean.class);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws Exception
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.AbstractResourceDescriptorGenerator#readTypeLevelConfiguration(com.github.nest.arcteryx.meta.IResourceDescriptor,
	 *      java.lang.Class)
	 */
	@Override
	protected void readTypeLevelConfiguration(IResourceDescriptor descriptor, Class<?> resourceClass) throws Exception {
		this.readTypeConstraints(resourceClass, (BeanDescriptor) descriptor);
	}

	/**
	 * read type constraints
	 * 
	 * @param beanClass
	 * @param descriptor
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected void readTypeConstraints(Class<?> beanClass, BeanDescriptor descriptor) throws Exception {
		// constraint reorganizer
		descriptor.setConstraintReorganizer(ConstraintUtils.readTypeConstraintReogranizer(beanClass));
		// constraints
		List<IConstraint> constraints = ConstraintUtils.generateConstraints(beanClass.getAnnotations());
		if (constraints != null && constraints.size() != 0) {
			descriptor.setConstraints(this.convertToBeanConstraints(constraints));
		}
	}

	/**
	 * convert to bean constraint list
	 * 
	 * @param constraints
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected List<IBeanConstraint> convertToBeanConstraints(List<IConstraint> constraints) {
		List<IBeanConstraint> list = new ArrayList<IBeanConstraint>(constraints.size());
		for (IConstraint constraint : constraints) {
			list.add((IBeanConstraint) constraint);
		}
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.annotation.AbstractResourceDescriptorGenerator#setProperties(com.github.nest.arcteryx.meta.IResourceDescriptor,
	 *      java.util.List)
	 */
	@Override
	protected void setProperties(IResourceDescriptor descriptor, List<IPropertyDescriptor> properties) {
		((BeanDescriptor) descriptor).setProperties(properties);
	}
}
