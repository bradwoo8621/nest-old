/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.List;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanCriteria;
import com.github.nest.arcteryx.meta.beans.IBeanFinder;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * spring ehcache bean finder
 * 
 * @author brad.wu
 */
public class SpringEhCacheBeanFinder extends AbstractStaticCodeBeanOperator implements IBeanFinder {
	/**
	 * if the parameter is<br>
	 * <ul>
	 * <li>{@linkplain IBeanIdentity}, call {@linkplain #find(IBeanIdentity)},</li>
	 * <li>{@linkplain IBeanCriteria}, call {@linkplain #find(IBeanCriteria)},</li>
	 * <li>otherwise, return parameter itself.</li>
	 * </ul>
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T handle(Object resource) {
		if (resource instanceof IBeanIdentity) {
			return find((IBeanIdentity) resource);
		} else if (resource instanceof IBeanCriteria) {
			return (T) find((IBeanCriteria) resource);
		} else {
			return (T) resource;
		}
	}

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
	 * @see com.github.nest.arcteryx.meta.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.AbstractStaticCodeBeanOperator#setResourceDescriptor(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void setResourceDescriptor(IResourceDescriptor resourceDescriptor) {
		assert resourceDescriptor instanceof ICachedBeanDescriptor : "Resource descriptor must be an instanceof "
				+ ICachedBeanDescriptor.class;

		super.setResourceDescriptor(resourceDescriptor);
	}
}
