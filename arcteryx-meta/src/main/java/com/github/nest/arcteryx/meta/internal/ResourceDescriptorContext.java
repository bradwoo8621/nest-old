/**
 * 
 */
package com.github.nest.arcteryx.meta.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.nest.arcteryx.meta.IDefaultOperatorProviderRegistry;
import com.github.nest.arcteryx.meta.IResouceDescriptorContextInterceptor;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.IResourceDescriptorContext;

/**
 * resource descriptor context
 * 
 * @author brad.wu
 */
public class ResourceDescriptorContext implements IResourceDescriptorContext {
	private Map<Class<?>, IResourceDescriptor> map = new HashMap<Class<?>, IResourceDescriptor>();
	private IResouceDescriptorContextInterceptor contextInterceptor = null;
	private IDefaultOperatorProviderRegistry operatorProvider = null;

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
	 * will set/clear context if the resource descriptor is instance of
	 * {@linkplain ResourceDescriptor}
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#put(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public IResourceDescriptor put(IResourceDescriptor descriptor) {
		assert descriptor != null : "Resource descriptor cannot be null.";

		synchronized (this.map) {
			IResourceDescriptor old = map.put(descriptor.getResourceClass(), descriptor);
			if (old instanceof ResourceDescriptor) {
				((ResourceDescriptor) old).setContext(null);
			}
			if (descriptor instanceof ResourceDescriptor) {
				((ResourceDescriptor) descriptor).setContext(this);
			}
			IResouceDescriptorContextInterceptor interceptor = this.getContextInterceptor();
			if (interceptor != null) {
				interceptor.postPutIntoContext(descriptor.getResourceClass(), descriptor);
			}
			return old;
		}
	}

	/**
	 * set mapping, return old mapping.<br>
	 * will set/clear context if the resource descriptor is instance of
	 * {@linkplain ResourceDescriptor}
	 * 
	 * @param descriptors
	 * @return old configuration
	 */
	public Collection<IResourceDescriptor> setDescriptors(Collection<IResourceDescriptor> descriptors) {
		assert descriptors != null : "Resource descriptor collection cannot be null.";

		synchronized (this.map) {
			List<IResourceDescriptor> oldDescriptors = new LinkedList<IResourceDescriptor>();
			oldDescriptors.addAll(this.map.values());

			this.map.clear();

			for (IResourceDescriptor oldDescriptor : oldDescriptors) {
				IResourceDescriptor descriptor = oldDescriptor;
				if (descriptor instanceof ResourceDescriptor) {
					((ResourceDescriptor) descriptor).setContext(null);
				}
			}
			for (IResourceDescriptor descriptor : descriptors) {
				if (descriptor instanceof ResourceDescriptor) {
					((ResourceDescriptor) descriptor).setContext(this);
				}
				this.map.put(descriptor.getResourceClass(), descriptor);
			}

			IResouceDescriptorContextInterceptor interceptor = this.getContextInterceptor();
			if (interceptor != null) {
				for (Map.Entry<Class<?>, IResourceDescriptor> entry : this.map.entrySet()) {
					interceptor.postPutIntoContext(entry.getKey(), entry.getValue());
				}
			}

			return oldDescriptors;
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IResourceDescriptorContext#getDefaultOperatorProvider()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IDefaultOperatorProviderRegistry> T getDefaultOperatorProvider() {
		if (this.operatorProvider == null) {
			synchronized (this) {
				if (this.operatorProvider == null) {
					this.operatorProvider = createDefaultOperatorProvider();
				}
			}
		}
		return (T) this.operatorProvider;
	}

	/**
	 * create default resource operator provider
	 * 
	 * @return
	 */
	protected IDefaultOperatorProviderRegistry createDefaultOperatorProvider() {
		return new DefaultOperatorProviderRegistry();
	}

	/**
	 * @param operatorProvider
	 *            the operatorProvider to set
	 */
	public void setOperatorProvider(IDefaultOperatorProviderRegistry operatorProvider) {
		this.operatorProvider = operatorProvider;
	}
}
