/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Map;

import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean creator
 * 
 * @author brad.wu
 */
public interface IBeanCreator extends IResourceOperator {
	String CODE = "meta.beans.creator";

	/**
	 * create resource by default values
	 * 
	 * @param descriptor
	 * @return
	 */
	<T> T create(IBeanDescriptor descriptor);

	/**
	 * create resource by initial values
	 * 
	 * @param descriptor
	 * @param initialValues
	 * @return
	 */
	<T> T create(IBeanDescriptor descriptor, Object... initialValues);

	/**
	 * create resource by initial values
	 * 
	 * @param descriptor
	 * @param initialValues
	 * @return
	 */
	<T> T create(IBeanDescriptor descriptor, Map<String, Object> initialValues);

	/**
	 * fill resource with default values
	 * 
	 * @param resource
	 * @param descriptor
	 * @return
	 */
	<T> T fillWithDefaultValues(T resource, IBeanDescriptor descriptor);
}
