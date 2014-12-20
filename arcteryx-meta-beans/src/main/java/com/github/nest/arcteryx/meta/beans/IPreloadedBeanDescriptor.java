/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

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
}
