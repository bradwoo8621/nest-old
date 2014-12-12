/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * Bean finder
 * 
 * @author brad.wu
 */
public interface IBeanFinder<In, Out> extends IResourceOperator<In, Out> {
	String CODE = "meta.beans.finder";

	/**
	 * find beans by given criteria
	 * 
	 * @param criteria
	 * @param descriptor
	 * @return
	 */
	List<Out> find(IBeanCriteria criteria, IResourceDescriptor descriptor);

	/**
	 * find bean by given key
	 * 
	 * @param key
	 * @param descriptor
	 * @return
	 */
	Out find(String key, IResourceDescriptor descriptor);
}
