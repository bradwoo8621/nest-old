/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * bean cache provider
 * 
 * @author brad.wu
 */
public interface IBeanCacheProvider extends IBeanOperator {
	/**
	 * put bean into cache
	 * 
	 * @param bean
	 */
	void put(Object bean);

	/**
	 * get bean from cache by given key
	 * 
	 * @param key
	 * @return
	 */
	<T> T get(IBeanIdentity key);
}
