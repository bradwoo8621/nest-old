/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean validator
 * 
 * @author brad.wu
 */
public interface IBeanValidator<In, Out> extends IResourceOperator<In, Out> {
	String CODE = "meta.beans.validator";

	/**
	 * validate resource
	 * 
	 * @param resource
	 * @param descriptor
	 * @return
	 */
	Out validate(In resource, IResourceDescriptor descriptor);
}
