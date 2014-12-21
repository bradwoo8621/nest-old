/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.ICachedBeanDescriptor;

/**
 * bean cache provider. {@linkplain #springContextId} is the spring context id
 * and {@linkplain #springCacheManagerId} is the bean id of EhCache manager id
 * in spring context. cache provide use the EhCache manager to put into and get
 * from beans from EhCache, and
 * 
 * @author brad.wu
 */
public class SpringEhCacheBeanCacheProvider extends AbstractStaticCodeBeanOperator implements IBeanCacheProvider {
	private String springContextId = null;
	private String springCacheManagerId = null;

	/**
	 * @return the springContextId
	 */
	public String getSpringContextId() {
		return springContextId;
	}

	/**
	 * @param springContextId
	 *            the springContextId to set
	 */
	public void setSpringContextId(String springContextId) {
		this.springContextId = springContextId;
	}

	/**
	 * @return the springCacheManagerId
	 */
	public String getSpringCacheManagerId() {
		return springCacheManagerId;
	}

	/**
	 * @param springCacheManagerId
	 *            the springCacheManagerId to set
	 */
	public void setSpringCacheManagerId(String springCacheManagerId) {
		this.springCacheManagerId = springCacheManagerId;
	}

	/**
	 * put resource into cache and return resource itself.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object)
	 */
	@Override
	public Object handle(Object resource) {
		this.put(resource);
		return resource;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#put(java.lang.Object)
	 */
	@Override
	public void put(Object bean) {
		Cache cache = getCache();

		IBeanIdentity id = null;
		Element element = new Element(id, bean);
		cache.put(element);
	}

	/**
	 * get cache
	 * 
	 * @return
	 */
	protected Cache getCache() {
		CacheManager cacheManager = Context.getContext(this.getSpringContextId()).getBean(
				this.getSpringCacheManagerId(), CacheManager.class);

		return cacheManager.getCache(this.getResourceDescriptor(ICachedBeanDescriptor.class).getCacheName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#get(com.github.nest.arcteryx.meta.beans.IBeanIdentity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(IBeanIdentity key) {
		Cache cache = getCache();
		Element element = cache.get(key);
		return (T) (element == null ? null : element.getObjectValue());
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
