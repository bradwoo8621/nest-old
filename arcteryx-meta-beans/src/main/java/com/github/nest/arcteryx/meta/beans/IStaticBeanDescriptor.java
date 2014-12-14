/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * static bean interface, which has an initializer to initialize the bean instances
 * 
 * @author brad.wu
 */
public interface IStaticBeanDescriptor extends IBeanDescriptor {
	/**
	 * get static resource initializer
	 * 
	 * @return
	 */
	IBeanInitializer getInitializer();
}
