/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * Resource operator interface, must be stateless. Resource operator doesn't
 * define the interface for how to handle resource, since it's uncertain.
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
	 * set resource descriptor
	 * 
	 * @param resourceDescriptor
	 */
	void setResourceDescriptor(IResourceDescriptor resourceDescriptor);

	/**
	 * get resource descriptor
	 * 
	 * @return
	 */
	<T extends IResourceDescriptor> T getResourceDescriptor();
}
