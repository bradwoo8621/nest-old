/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * cached bean descriptor
 * 
 * @author brad.wu
 */
public class CachedBeanDescriptor extends BeanDescriptor implements ICachedBeanDescriptor {
	private static final long serialVersionUID = 5153757122375819187L;

	private String cacheName = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#getCacheName()
	 */
	@Override
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#getFromCache(com.github.nest.arcteryx.meta.beans.IBeanIdentity)
	 */
	@Override
	public <T> T getFromCache(IBeanIdentity key) {
		return this.getCacheProvider().getFromCache(key);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#putIntoCache(java.lang.Object)
	 */
	@Override
	public void putIntoCache(Object resource) {
		this.getCacheProvider().putIntoCache(resource);
	}
}
