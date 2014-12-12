/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.Collection;

/**
 * static resource initializer
 * 
 * @author brad.wu
 */
public interface IStaticResourceInitializer extends IResourceOperator {
	String CODE = "meta.initializer";

	/**
	 * initialize static resources
	 * 
	 * @param descriptor
	 * @return
	 */
	Collection<Object> initialize(IResourceDescriptor descriptor);
}
