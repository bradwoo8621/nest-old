/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.List;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanCriteria;
import com.github.nest.arcteryx.meta.beans.IBeanFinder;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * cached bean finder. must be registered in {@linkplain ICachedBeanDescriptor},
 * and use {@linkplain ICachedBeanDescriptor#getCacheProvider()} to find the
 * bean by given identity.
 * 
 * @author brad.wu
 */
public class CachedBeanFinder extends AbstractStaticCodeBeanOperator implements IBeanFinder {
	/**
	 * Not supported yet. will throw {@linkplain UnsupportedOperationException}
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanFinder#find(com.github.nest.arcteryx.meta.beans.IBeanCriteria)
	 */
	@Override
	public <T> List<T> find(IBeanCriteria criteria) {
		// TODO not supported yet, since criteria is on the way.
		throw new UnsupportedOperationException("Not supported yet, use IBeanIdentity as paramter.");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanFinder#find(com.github.nest.arcteryx.meta.beans.IBeanIdentity)
	 */
	@Override
	public <T> T find(IBeanIdentity beanIdentity) {
		ICachedBeanDescriptor descriptor = this.getResourceDescriptor();

		IBeanCacheProvider provider = descriptor.getCacheProvider();
		return provider.getFromCache(beanIdentity);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.AbstractStaticCodeBeanOperator#setResourceDescriptor(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void setResourceDescriptor(IResourceDescriptor resourceDescriptor) {
		assert resourceDescriptor instanceof ICachedBeanDescriptor : "Resource descriptor must be an instanceof "
				+ ICachedBeanDescriptor.class;

		super.setResourceDescriptor(resourceDescriptor);
	}
}
