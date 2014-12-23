/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;

/**
 * Preloaded bean interface, which has an initializer to initialize the bean
 * instances
 * 
 * @author brad.wu
 */
public interface IPreloadedBeanDescriptor extends IBeanDescriptor {
	/**
	 * get static resource preloader
	 * 
	 * @return
	 */
	IBeanPreloader getPreloader();

	/**
	 * get preloaded beans
	 * 
	 * @return
	 */
	<T> Collection<T> getPreloadedBeans();

	/**
	 * set preloaded beans
	 * 
	 * @param beans
	 */
	void setPreloadedBeans(Collection<?> beans);
}
