/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;
import java.util.List;

/**
 * bean comparator interface
 * 
 * @author brad.wu
 */
public interface IBeanSorter extends IBeanOperator {
	/**
	 * sort result will be cached or not
	 * 
	 * @return
	 */
	boolean isCached();

	/**
	 * sort beans, can be used in {@linkplain ICachedBeanDescriptor} which will
	 * hold the beans.
	 * 
	 * @return
	 */
	<T> List<T> sort();

	/**
	 * sort beans
	 * 
	 * @param beans
	 * @return
	 */
	<T> List<T> sort(Collection<T> beans);
}
