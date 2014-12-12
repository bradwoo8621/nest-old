/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Map;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean creator
 * 
 * @author brad.wu
 */
public interface IBeanCreator<In, Out> extends IResourceOperator<In, Out> {
	String CODE = "meta.beans.creator";

	/**
	 * create resource by default values
	 * 
	 * @param descriptor
	 * @return
	 */
	Out create(IResourceDescriptor descriptor);

	/**
	 * create resource by initial values
	 * 
	 * @param descriptor
	 * @param initialValues
	 * @return
	 */
	Out create(IResourceDescriptor descriptor, Object... initialValues);

	/**
	 * create resource by initial values
	 * 
	 * @param descriptor
	 * @param initialValues
	 * @return
	 */
	Out create(IResourceDescriptor descriptor, Map<String, Object> initialValues);

	/**
	 * fill resource with default values
	 * 
	 * @param resource
	 * @param descriptor
	 * @return
	 */
	Out fillWithDefaultValues(In resource, IResourceDescriptor descriptor);
}
