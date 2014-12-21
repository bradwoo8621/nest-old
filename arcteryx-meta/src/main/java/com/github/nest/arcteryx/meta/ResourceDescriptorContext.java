/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * resource descriptor repository
 * 
 * @author brad.wu
 */
public class ResourceDescriptorContext implements IResourceDescriptorRepository {
	private Map<Class<?>, IResourceDescriptor> map = new HashMap<Class<?>, IResourceDescriptor>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorRepository#get(java.lang.Object)
	 */
	@Override
	public IResourceDescriptor get(Object resource) {
		assert resource != null : "Resource instance cannot be null.";

		Class<?> clazz = resource.getClass();
		return get(clazz);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorRepository#get(java.lang.Class)
	 */
	@Override
	public IResourceDescriptor get(Class<?> resourceClass) {
		assert resourceClass != null : "Resource class cannot be null.";

		IResourceDescriptor descriptor = map.get(resourceClass);
		if (descriptor != null) {
			return descriptor;
		} else {
			// find by super class
			Class<?> superClass = resourceClass.getSuperclass();
			if (superClass != null) {
				descriptor = get(superClass);
			}

			// not defined by super class or no super class
			if (descriptor != null) {
				return descriptor;
			} else {
				// find by interfaces, traverse all interfaces and their
				// interfaces until find the first interface with descriptor
				Class<?>[] interfaces = resourceClass.getInterfaces();
				for (Class<?> interfaceClass : interfaces) {
					descriptor = get(interfaceClass);
					if (descriptor != null) {
						return descriptor;
					}
				}
			}

			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorRepository#put(java.lang.Class,
	 *      com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public IResourceDescriptor put(Class<?> resourceClass, IResourceDescriptor descriptor) {
		assert resourceClass != null : "Resource class cannot be null.";
		assert descriptor != null : "Resource descriptor cannot be null.";

		synchronized (this.map) {
			return map.put(resourceClass, descriptor);
		}
	}

	/**
	 * set mapping, return old mapping.
	 * 
	 * @param map
	 * @return old configuration
	 */
	public Map<Class<?>, IResourceDescriptor> setMapping(Map<Class<?>, IResourceDescriptor> map) {
		assert map != null : "Resource descriptor map cannot be null.";

		synchronized (this.map) {
			Map<Class<?>, IResourceDescriptor> oldMap = new HashMap<Class<?>, IResourceDescriptor>();
			oldMap.putAll(this.map);

			this.map.clear();
			this.map.putAll(map);

			return oldMap;
		}
	}
}
