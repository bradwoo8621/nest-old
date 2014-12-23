/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;
import java.util.List;

/**
 * cached bean descriptor
 * 
 * @author brad.wu
 */
public interface ICachedBeanDescriptor extends IBeanDescriptor {
	/**
	 * get cache provider
	 * 
	 * @return
	 */
	IBeanCacheProvider getCacheProvider();

	/**
	 * get sorter
	 * 
	 * @param code
	 * @return
	 */
	IBeanSorter getSorter(String code);

	/**
	 * get default sorter code
	 * 
	 * @return
	 */
	String getDefaultSorterCode();

	/**
	 * get cache name
	 * 
	 * @return
	 */
	String getCacheName();

	/**
	 * get preloaded beans
	 * 
	 * @return
	 */
	<T> Collection<T> getBeans();

	/**
	 * get sorted beans
	 * 
	 * @param sorterCode
	 * @return
	 */
	<T> List<T> getSortedBeans(String sorterCode);

	/**
	 * get sorted beans
	 * 
	 * @return
	 */
	<T> List<T> getSortedBeans();

	/**
	 * get from cache by given identity
	 * 
	 * @param key
	 * @return
	 */
	<T> T getFromCache(IBeanIdentity key);
}
