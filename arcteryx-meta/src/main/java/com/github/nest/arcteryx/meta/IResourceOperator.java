/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * Resource operator interface, must be stateless.
 * 
 * @author brad.wu
 */
public interface IResourceOperator {
	/**
	 * get code
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * handle resource instance
	 * 
	 * @param resource
	 * @param descriptor
	 * @return
	 */
	Object handle(Object resource, IResourceDescriptor descriptor);
}
