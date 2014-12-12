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
public interface IStaticResourceInitializer<In, Out> extends IResourceOperator<In, Out> {
	String CODE = "meta.initializer";

	/**
	 * initialize static resources
	 * 
	 * @param descriptor
	 * @return
	 */
	Collection<Out> initialize(IResourceDescriptor descriptor);
}
