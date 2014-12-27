/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Collection;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.IBeanIdentityExtracter;
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
	private boolean initialized = false;

	private String springContextId = null;
	private String springCacheManagerId = null;
	private IBeanIdentityExtracter identityExtracter = null;

	private Collection<?> beans = null;

	/**
	 * Spring context id of cache manager bean
	 * 
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
	 * Spring bean id of cache manager bean
	 * 
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
	 * initialize beans
	 * 
	 * @return
	 */
	protected void initialize() {
		if (!this.isInitialized()) {
			synchronized (this) {
				if (!this.isInitialized()) {
					SpringCachedBeanDescriptor springDescriptor = getResourceDescriptor();
					String beanId = springDescriptor.getSpringBeanId();
					String contextId = springDescriptor.getSpringContextId();
					this.beans = (Collection<?>) Context.getContext(contextId).getBean(beanId);
					for (Object bean : beans) {
						this.putIntoCache(bean);
					}
					this.setInitialized(true);
				}
			}
		}
	}

	/**
	 * @return the initialized
	 */
	protected boolean isInitialized() {
		return initialized;
	}

	/**
	 * @param initialized
	 *            the initialized to set
	 */
	protected void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getBeans()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getBeans() {
		if (!this.isInitialized()) {
			this.initialize();
		}
		return (Collection<T>) this.beans;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getIdentityExtracter()
	 */
	@Override
	public IBeanIdentityExtracter getIdentityExtracter() {
		return this.identityExtracter;
	}

	/**
	 * @param identityExtracter
	 *            the identityExtracter to set
	 */
	public void setIdentityExtracter(IBeanIdentityExtracter identityExtracter) {
		this.identityExtracter = identityExtracter;
	}

	/**
	 * if the parameter is an instance of {@linkplain IBeanIdentity}, get object
	 * from cache by the identity; otherwise return itself.
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceOperator#handle(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T handle(Object parameter) {
		assert parameter != null : "Parameter cannot be null.";

		if (parameter instanceof IBeanIdentity) {
			return this.getFromCache((IBeanIdentity) parameter);
		} else {
			return (T) parameter;
		}
	}

	/**
	 * put into cache
	 * 
	 * @param bean
	 */
	protected void putIntoCache(Object bean) {
		Cache cache = getCache();

		IBeanIdentity id = this.getIdentityExtracter().extract(bean);
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
		ICachedBeanDescriptor descriptor = this.getResourceDescriptor();
		return cacheManager.getCache(descriptor.getCacheName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getFromCache(com.github.nest.arcteryx.meta.beans.IBeanIdentity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFromCache(IBeanIdentity key) {
		Cache cache = getCache();
		Element element = cache.get(key);
		if (element == null && !this.isInitialized()) {
			this.initialize();
			return this.getFromCache(key);
		} else {
			return (T) element.getObjectValue();
		}
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
		assert resourceDescriptor instanceof SpringCachedBeanDescriptor : "Resource descriptor must be an instanceof "
				+ SpringCachedBeanDescriptor.class;

		super.setResourceDescriptor(resourceDescriptor);
	}
}
