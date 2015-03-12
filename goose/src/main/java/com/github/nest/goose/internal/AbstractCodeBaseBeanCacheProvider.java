/**
 * 
 */
package com.github.nest.goose.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.nest.arcteryx.meta.beans.IBeanCacheProvider;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.IBeanIdentityExtracter;
import com.github.nest.arcteryx.meta.beans.internal.AbstractBeanOperator;

/**
 * code based bean cache provider
 * 
 * @author brad.wu
 */
public abstract class AbstractCodeBaseBeanCacheProvider extends AbstractBeanOperator implements IBeanCacheProvider {
	private Map<String, Object> cache = new HashMap<String, Object>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.AbstractResourceOperator#getCode()
	 */
	@Override
	public String getCode() {
		return IBeanCacheProvider.CODE;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getFromCache(com.github.nest.arcteryx.meta.beans.IBeanIdentity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFromCache(IBeanIdentity key) {
		assert key instanceof Code : "Bean identity must be an instance of [" + Code.class + "].";

		String code = ((Code) key).getCode();

		buildCache();

		return (T) cache.get(code);
	}

	/**
	 * build cache
	 */
	protected void buildCache() {
		if (cache.size() == 0) {
			synchronized (this) {
				if (cache.size() == 0) {
					Collection<Object> beans = this.getBeans();
					IBeanIdentityExtracter extracter = this.getIdentityExtracter();
					for (Object bean : beans) {
						Code id = extracter.extract(bean);
						cache.put(id.getCode(), bean);
					}
				}
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getIdentityExtracter()
	 */
	@Override
	public IBeanIdentityExtracter getIdentityExtracter() {
		return new CodeExtracter();
	}
}
