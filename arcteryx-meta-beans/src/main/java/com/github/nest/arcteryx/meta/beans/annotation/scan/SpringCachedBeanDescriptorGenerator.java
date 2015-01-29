/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxSpringCachedBean;
import com.github.nest.arcteryx.meta.beans.internal.SpringCachedBeanDescriptor;

/**
 * spring cached bean descriptor generator
 * 
 * @author brad.wu
 */
public class SpringCachedBeanDescriptorGenerator extends CachedBeanDescriptorGenerator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.annotation.scan.CachedBeanDescriptorGenerator#getAnnotationDelcareBean(java.lang.Class)
	 */
	@Override
	protected Annotation getAnnotationDelcareBean(Class<?> beanClass) {
		return beanClass.getAnnotation(ArcteryxSpringCachedBean.class);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.annotation.scan.CachedBeanDescriptorGenerator#createDescriptor(java.lang.annotation.Annotation,
	 *      java.lang.Class)
	 */
	@Override
	protected IResourceDescriptor createDescriptor(Annotation annotation, Class<?> resourceClass) {
		ArcteryxSpringCachedBean bean = (ArcteryxSpringCachedBean) annotation;
		SpringCachedBeanDescriptor descriptor = new SpringCachedBeanDescriptor();
		descriptor.setName(bean.name());
		descriptor.setBeanClass(resourceClass);
		descriptor.setDescription(bean.description());
		descriptor.setCacheName(bean.cacheName());
		descriptor.setDefaultSorterCode(bean.defaultSorterCode());
		descriptor.setSpringContextId(bean.springContextId());
		descriptor.setSpringBeanId(bean.springBeanId());
		return descriptor;
	}
}
