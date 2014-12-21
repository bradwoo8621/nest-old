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
	String CODE = "meta.beans.cacheProvider";

	/**
	 * put bean into cache
	 * 
	 * @param bean
	 */
	void putIntoCache(Object bean);

	/**
	 * get bean from cache by given key
	 * 
	 * @param key
	 * @return
	 */
	<T> T getFromCache(IBeanIdentity key);

	/**
	 * get bean identity extracter
	 * 
	 * @return
	 */
	IBeanIdentityExtracter getIdentityExtracter();
}
