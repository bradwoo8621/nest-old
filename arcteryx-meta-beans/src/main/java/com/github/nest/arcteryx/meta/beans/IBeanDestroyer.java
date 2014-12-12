/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean destroyer
 * 
 * @author brad.wu
 */
public interface IBeanDestroyer extends IResourceOperator {
	String CODE = "meta.beans.destroyer";

	/**
	 * destroy resource
	 * 
	 * @param resource
	 * @param descritpor
	 * @return
	 */
	boolean destroy(Object resource, IBeanDescriptor descritpor);
}
