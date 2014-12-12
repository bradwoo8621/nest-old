/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * static resource interface
 * 
 * @author brad.wu
 */
public interface IStaticResourceDescriptor extends IResourceDescriptor {
	/**
	 * initialize the resource instances
	 * 
	 * @throws ResourceException
	 */
	void initialize() throws ResourceException;
}
