/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * Resource operator interface, must be stateless.
 * 
 * @author brad.wu
 */
public interface IResourceOperator<In, Out> {
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
	 * @throws ResourceExecutionException
	 */
	Out handle(In resource);
}
