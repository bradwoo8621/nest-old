/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Map;

/**
 * Bean creator
 * 
 * @author brad.wu
 */
public interface IBeanCreator extends IBeanOperator {
	String CODE = "meta.beans.creator";

	/**
	 * create resource by default values
	 * 
	 * @return
	 */
	<T> T create();

	/**
	 * create resource by initial values
	 * 
	 * @param initialValues
	 * @return
	 */
	<T> T create(Object... initialValues);

	/**
	 * create resource by given constructor and initial values
	 * 
	 * @param constructorParameterTypes
	 * @param initialValues
	 * @return
	 */
	<T> T create(Class<?>[] constructorParameterTypes, Object[] initialValues);

	/**
	 * create resource by initial values
	 * 
	 * @param initialValues
	 * @return
	 */
	<T> T create(Map<String, Object> initialValues);

	/**
	 * fill resource with default values
	 * 
	 * @param resource
	 * @return
	 */
	<T> T fillWithDefaultValues(T resource);
}
