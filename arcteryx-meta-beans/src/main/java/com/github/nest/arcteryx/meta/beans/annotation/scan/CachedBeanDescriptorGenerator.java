/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation.scan;

import java.lang.annotation.Annotation;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxCachedBean;
import com.github.nest.arcteryx.meta.beans.internal.CachedBeanDescriptor;

/**
 * cached bean descriptor generator
 * 
 * @author brad.wu
 */
public class CachedBeanDescriptorGenerator extends BeanDescriptorGenerator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.annotation.scan.BeanDescriptorGenerator#getAnnotationDelcareBean(java.lang.Class)
	 */
	@Override
	protected Annotation getAnnotationDelcareBean(Class<?> beanClass) {
		return beanClass.getAnnotation(ArcteryxCachedBean.class);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.annotation.scan.BeanDescriptorGenerator#createDescriptor(java.lang.annotation.Annotation,
	 *      java.lang.Class)
	 */
	@Override
	protected IResourceDescriptor createDescriptor(Annotation annotation, Class<?> resourceClass) {
		ArcteryxCachedBean bean = (ArcteryxCachedBean) annotation;
		CachedBeanDescriptor descriptor = new CachedBeanDescriptor();
		descriptor.setName(bean.name());
		descriptor.setBeanClass(resourceClass);
		descriptor.setDescription(bean.description());
		descriptor.setCacheName(bean.cacheName());
		descriptor.setDefaultSorterCode(bean.defaultSorterCode());
		return descriptor;
	}
}
