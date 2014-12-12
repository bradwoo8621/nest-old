/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean destroyer
 * 
 * @author brad.wu
 */
public interface IBeanDestroyer<In, Out> extends IResourceOperator<In, Out> {
	String CODE = "meta.beans.destroyer";

	/**
	 * destroy resource
	 * 
	 * @param resource
	 * @param descritpor
	 * @return
	 */
	boolean destroy(In resource, IResourceDescriptor descritpor);
}
