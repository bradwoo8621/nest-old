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
	 * get static resource initializer
	 * 
	 * @return
	 */
	IStaticResourceInitializer getInitializer();
}
