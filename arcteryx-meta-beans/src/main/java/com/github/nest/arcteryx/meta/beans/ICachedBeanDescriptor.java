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

	String getCacheName();
}
