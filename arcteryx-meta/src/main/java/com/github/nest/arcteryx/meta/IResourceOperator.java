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
	IResourceDescriptor getResourceDescriptor();

	/**
	 * handle resource instance
	 * 
	 * @param resource
	 * @param descriptor
	 * @return
	 */
	Object handle(Object resource);
}
