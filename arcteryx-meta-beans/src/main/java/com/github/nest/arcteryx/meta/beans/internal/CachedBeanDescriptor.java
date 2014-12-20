/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * cached bean descriptor
 * 
 * @author brad.wu
 */
public class CachedBeanDescriptor extends BeanDescriptor implements ICachedBeanDescriptor {
	private static final long serialVersionUID = 5153757122375819187L;

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
