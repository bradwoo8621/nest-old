/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Collection;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.IBeanSorter;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * cached bean descriptor
 * 
 * @author brad.wu
 */
@SuppressWarnings("unchecked")
public class CachedBeanDescriptor extends BeanDescriptor implements ICachedBeanDescriptor {
	private static final long serialVersionUID = 5153757122375819187L;

	private String cacheName = null;
	private String defaultSorterCode = null;

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
		return this.getOperator(IBeanCacheProvider.CODE);
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
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#getSorter(java.lang.String)
	 */
	@Override
	public IBeanSorter getSorter(String code) {
		return this.getOperator(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#getDefaultSorterCode()
	 */
	@Override
	public String getDefaultSorterCode() {
		return defaultSorterCode;
	}

	/**
	 * normally the {@linkplain IBeanSorter#isCached()} should be true to
	 * improve the performance
	 * 
	 * @param defaultSorterCode
	 *            the defaultSorterCode to set
	 */
	public void setDefaultSorterCode(String defaultSorterCode) {
		this.defaultSorterCode = defaultSorterCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor#getBeans()
	 */
	@Override
	public <T> Collection<T> getBeans() {
		return this.getCacheProvider().getBeans();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#getSortedBeans(java.lang.String)
	 */
	@Override
	public <T> List<T> getSortedBeans(String sorterCode) {
		return (List<T>) getSorter(sorterCode).sort();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#getSortedBeans()
	 */
	@Override
	public <T> List<T> getSortedBeans() {
		return (List<T>) getSorter(this.getDefaultSorterCode()).sort();
	}
}
