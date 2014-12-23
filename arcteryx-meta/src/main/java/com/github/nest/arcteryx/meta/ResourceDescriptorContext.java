/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * resource descriptor context
 * 
 * @author brad.wu
 */
public class ResourceDescriptorContext implements IResourceDescriptorContext {
	private Map<Class<? extends IResourceDescriptor>, IResourceDescriptor> map = new HashMap<Class<? extends IResourceDescriptor>, IResourceDescriptor>();
	private IResouceDescriptorContextInterceptor contextInterceptor = null;

	/**
	 * @return the contextInterceptor
	 */
	public IResouceDescriptorContextInterceptor getContextInterceptor() {
		return contextInterceptor;
	}

	/**
	 * @param contextInterceptor
	 *            the contextInterceptor to set
	 */
	public void setContextInterceptor(IResouceDescriptorContextInterceptor contextInterceptor) {
		this.contextInterceptor = contextInterceptor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#get(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceDescriptor> T get(Object resource) {
		assert resource != null : "Resource instance cannot be null.";

		Class<?> clazz = resource.getClass();
		IResourceDescriptor descriptor = get(clazz);
		IResouceDescriptorContextInterceptor interceptor = this.getContextInterceptor();
		if (interceptor != null) {
			return (T) interceptor.decorateDescriptor(descriptor);
		} else {
			return (T) descriptor;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#get(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IResourceDescriptor> T get(Class<?> resourceClass) {
		assert resourceClass != null : "Resource class cannot be null.";

		IResourceDescriptor descriptor = map.get(resourceClass);
		if (descriptor != null) {
			return (T) descriptor;
		} else {
			// find by super class
			Class<?> superClass = resourceClass.getSuperclass();
			if (superClass != null) {
				descriptor = get(superClass);
			}

			// not defined by super class or no super class
			if (descriptor != null) {
				return (T) descriptor;
			} else {
				// find by interfaces, traverse all interfaces and their
				// interfaces until find the first interface with descriptor
				Class<?>[] interfaces = resourceClass.getInterfaces();
				for (Class<?> interfaceClass : interfaces) {
					descriptor = get(interfaceClass);
					if (descriptor != null) {
						return (T) descriptor;
					}
				}
			}

			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#put(java.lang.Class,
	 *      com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public IResourceDescriptor put(Class<? extends IResourceDescriptor> resourceClass, IResourceDescriptor descriptor) {
		assert resourceClass != null : "Resource class cannot be null.";
		assert descriptor != null : "Resource descriptor cannot be null.";

		synchronized (this.map) {
			IResourceDescriptor old = map.put(resourceClass, descriptor);
			IResouceDescriptorContextInterceptor interceptor = this.getContextInterceptor();
			if (interceptor != null) {
				interceptor.postPutIntoContext(resourceClass, descriptor);
			}
			return old;
		}
	}

	/**
	 * set mapping, return old mapping.
	 * 
	 * @param map
	 * @return old configuration
	 */
	public Map<Class<? extends IResourceDescriptor>, IResourceDescriptor> setMapping(
			Map<Class<? extends IResourceDescriptor>, IResourceDescriptor> map) {
		assert map != null : "Resource descriptor map cannot be null.";

		synchronized (this.map) {
			Map<Class<? extends IResourceDescriptor>, IResourceDescriptor> oldMap = new HashMap<Class<? extends IResourceDescriptor>, IResourceDescriptor>();
			oldMap.putAll(this.map);

			this.map.clear();
			this.map.putAll(map);

			IResouceDescriptorContextInterceptor interceptor = this.getContextInterceptor();
			if (interceptor != null) {
				for (Map.Entry<Class<? extends IResourceDescriptor>, IResourceDescriptor> entry : this.map.entrySet()) {
					interceptor.postPutIntoContext(entry.getKey(), entry.getValue());
				}
			}

			return oldMap;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#getDescriptors()
	 */
	@Override
	public Collection<IResourceDescriptor> getDescriptors() {
		List<IResourceDescriptor> list = new ArrayList<IResourceDescriptor>(map.size());
		list.addAll(map.values());
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#getDescriptors(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getDescriptors(Class<T> descriptorClass) {
		List<T> list = new ArrayList<T>(map.size());
		for (IResourceDescriptor descriptor : map.values()) {
			if (descriptorClass.isAssignableFrom(descriptor.getClass())) {
				list.add((T) descriptor);
			}
		}
		return list;
	}
}
