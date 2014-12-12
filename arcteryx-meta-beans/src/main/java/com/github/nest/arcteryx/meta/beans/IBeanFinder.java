/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean finder
 * 
 * @author brad.wu
 */
public interface IBeanFinder extends IResourceOperator {
	String CODE = "meta.beans.finder";

	/**
	 * find beans by given criteria
	 * 
	 * @param criteria
	 * @param descriptor
	 * @return
	 */
	<T> List<T> find(IBeanCriteria criteria, IBeanDescriptor descriptor);

	/**
	 * find bean by given key
	 * 
	 * @param key
	 * @param descriptor
	 * @return
	 */
	<T> T find(String key, IBeanDescriptor descriptor);
}
