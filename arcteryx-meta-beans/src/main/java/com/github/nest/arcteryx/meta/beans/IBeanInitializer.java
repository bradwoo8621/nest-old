/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * bean initializer
 * 
 * @author brad.wu
 */
public interface IBeanInitializer extends IResourceOperator {
	String CODE = "meta.beans.initializer";

	/**
	 * initialize static resources
	 * 
	 * @param descriptor
	 * @return
	 */
	Collection<Object> initialize(IResourceDescriptor descriptor);
}
