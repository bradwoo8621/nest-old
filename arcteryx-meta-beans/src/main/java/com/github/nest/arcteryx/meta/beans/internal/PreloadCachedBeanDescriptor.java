/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanPreloader;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor;

/**
 * preload and cached bean descriptor
 * 
 * @author brad.wu
 */
public class PreloadCachedBeanDescriptor extends BeanDescriptor implements IPreloadedBeanDescriptor,
		ICachedBeanDescriptor {
	private static final long serialVersionUID = 935037038393292356L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IPreloadedBeanDescriptor#getPreloader()
	 */
	@Override
	public IBeanPreloader getPreloader() {
		return this.getOperator(IBeanPreloader.CODE, IBeanPreloader.class);
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
