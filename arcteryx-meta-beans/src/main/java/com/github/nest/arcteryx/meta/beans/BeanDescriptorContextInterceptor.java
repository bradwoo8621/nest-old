/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.Collection;

import com.github.nest.arcteryx.meta.IResouceDescriptorContextInterceptor;
import com.github.nest.arcteryx.meta.IResourceDescriptor;

/**
 * bean descriptor context interceptor
 * 
 * @author brad.wu
 */
public class BeanDescriptorContextInterceptor implements IResouceDescriptorContextInterceptor {
	/**
	 * when the resource descriptor is put into context,
	 * <ul>
	 * <li>Find {@linkplain IBeanPreloader} by {@linkplain IBeanPreloader#CODE},
	 * and load beans,</li>
	 * <li>Find {@linkplain IBeanCacheProvider} by
	 * {@linkplain IBeanCacheProvider#CODE}, and cache beans.</li>
	 * 
	 * @see com.github.nest.arcteryx.meta.IResouceDescriptorContextInterceptor#postPutIntoContext(java.lang.Class,
	 *      com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void postPutIntoContext(Class<?> resourceClass, IResourceDescriptor descriptor) {
		Collection<?> beans = null;
		IBeanPreloader preloader = descriptor.getOperator(IBeanPreloader.CODE, IBeanPreloader.class);
		if (preloader != null) {
			beans = preloader.load();
		}

		IBeanCacheProvider provider = descriptor.getOperator(IBeanCacheProvider.CODE, IBeanCacheProvider.class);
		if (provider != null) {
			for (Object bean : beans) {
				provider.putIntoCache(bean);
			}
		}
	}
}
