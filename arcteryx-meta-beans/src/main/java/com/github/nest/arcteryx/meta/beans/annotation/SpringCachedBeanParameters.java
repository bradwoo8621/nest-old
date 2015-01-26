/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

/**
 * spring cached bean parameters
 * 
 * @author brad.wu
 */
public @interface SpringCachedBeanParameters {
	/**
	 * spring context id
	 * 
	 * @return
	 */
	String springContextId();

	/**
	 * spring bean id
	 * 
	 * @return
	 */
	String springBeanId();
}
