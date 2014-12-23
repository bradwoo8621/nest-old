/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;

/**
 * bean cache provider
 * 
 * @author brad.wu
 */
public interface IBeanCacheProvider extends IBeanOperator {
	String CODE = "meta.beans.cacheProvider";

	/**
	 * get beans
	 * 
	 * @return
	 */
	<T> Collection<T> getBeans();

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
