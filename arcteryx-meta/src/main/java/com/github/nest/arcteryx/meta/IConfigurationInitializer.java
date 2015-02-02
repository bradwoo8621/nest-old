/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * configuration initializer
 * 
 * @author brad.wu
 */
public interface IConfigurationInitializer<T> {
	/**
	 * initialize
	 * 
	 * @param context
	 * @return
	 */
	T initialize(IResourceDescriptorContext context);

	/**
	 * get return value key
	 * 
	 * @return
	 */
	String getReturnValueKey();
}
