/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Collection;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.meta.beans.IBeanPreloader;

/**
 * bean preloader by spring
 * 
 * @author brad.wu
 */
public class SpringBeanPreloader extends AbstractStaticCodeBeanOperator implements IBeanPreloader {
	/**
	 * do nothing and simply return resource itself.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object)
	 */
	@Override
	public Object handle(Object resource) {
		return resource;
	}

	/**
	 * the parameter <code>descriptor</code> must be an instance of
	 * {@linkplain SpringPreloadedBeanDescriptor}, preloader will read the
	 * property <code>springContextId</code> and <code>springBeanId</code> and
	 * return the bean as result, which means the bean in spring must be an
	 * instance of {@linkplain Collection}.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPreloader#load(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> load(IResourceDescriptor descriptor) {
		assert descriptor instanceof SpringPreloadedBeanDescriptor : "Descriptor must be an instance of "
				+ SpringPreloadedBeanDescriptor.class.getName();

		SpringPreloadedBeanDescriptor springDescriptor = (SpringPreloadedBeanDescriptor) descriptor;
		String beanId = springDescriptor.getSpringBeanId();
		String contextId = springDescriptor.getSpringContextId();
		return (Collection<T>) Context.getContext(contextId).getBean(beanId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
	}
}
