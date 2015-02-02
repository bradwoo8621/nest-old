/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.io.Serializable;

/**
 * configuration initializer
 * 
 * @author brad.wu
 */
public interface IConfigurationInitializer<T> extends Serializable {
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
