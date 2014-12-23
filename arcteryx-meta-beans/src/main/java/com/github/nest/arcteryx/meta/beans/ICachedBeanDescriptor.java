/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

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
	 * get cache name
	 * 
	 * @return
	 */
	String getCacheName();

	/**
	 * get from cache by given identity
	 * 
	 * @param key
	 * @return
	 */
	<T> T getFromCache(IBeanIdentity key);

	/**
	 * put the resource into cache
	 * 
	 * @param resource
	 */
	void putIntoCache(Object resource);
}
