/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * configuration initializer
 * 
 * @author brad.wu
 */
public interface IConfigurationInitializer<T, C extends IResourceDescriptorContext> {
	/**
	 * initialize
	 * 
	 * @param context
	 * @return
	 */
	T initialize(C context);

	/**
	 * get return value key
	 * 
	 * @return
	 */
	String getReturnValueKey();
}
