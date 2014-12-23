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
	<T extends IResourceDescriptor> T getResourceDescriptor();

	/**
	 * handle parameter, see its implementation's explanation
	 * 
	 * @param parameter
	 * @param descriptor
	 * @return
	 */
	<T> T handle(Object parameter);
}
