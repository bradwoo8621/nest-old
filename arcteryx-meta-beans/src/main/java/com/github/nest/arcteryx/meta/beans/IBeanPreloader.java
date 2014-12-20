/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * bean preloader
 * 
 * @author brad.wu
 */
public interface IBeanPreloader extends IResourceOperator {
	String CODE = "meta.beans.preloader";

	/**
	 * load static resources
	 * 
	 * @param descriptor
	 * @return
	 */
	Collection<Object> load(IResourceDescriptor descriptor);
}
