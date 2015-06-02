/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import org.restlet.ext.jaxrs.ObjectFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * application
 * 
 * @author brad.wu
 * @author yi.feng
 */
public class SpringObjectFactory implements ObjectFactory, ApplicationContextAware {
	private ApplicationContext applicationContext;

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.ext.jaxrs.ObjectFactory#getInstance(java.lang.Class)
	 */
	@Override
	public <T> T getInstance(final Class<T> jaxRsClass) {
		T bean = this.getApplicationContext().getBean(jaxRsClass);
		return bean;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
