/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * spring preloaded and cached bean descriptor
 * 
 * @author brad.wu
 */
public class SpringPreloadedNCachedBeanDescriptor extends SpringPreloadedBeanDescriptor implements
		ICachedBeanDescriptor {
	private static final long serialVersionUID = -4556948016396855983L;
	private String cacheName = null;

	/**
	 * @return the cacheName
	 */
	public String getCacheName() {
		return cacheName;
	}

	/**
	 * @param cacheName
	 *            the cacheName to set
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#getCacheProvider()
	 */
	@Override
	public IBeanCacheProvider getCacheProvider() {
		return this.getOperator(IBeanCacheProvider.CODE, IBeanCacheProvider.class);
	}
}
