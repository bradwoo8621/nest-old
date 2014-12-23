/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.Collection;

/**
 * resource descriptor repository interface
 * 
 * @author brad.wu
 */
public interface IResourceDescriptorContext {
	/**
	 * get descriptor by given resource instance
	 * 
	 * @param resource
	 * @return
	 */
	IResourceDescriptor get(Object resource);

	/**
	 * get descriptor by given resource instance and descriptor class
	 * 
	 * @param resource
	 * @param descriptorClass
	 * @return
	 */
	<T extends IResourceDescriptor> T get(Object resource, Class<T> descriptorClass);

	/**
	 * get descriptor by given resource class
	 * 
	 * @param resourceClass
	 * @return
	 */
	IResourceDescriptor get(Class<?> resourceClass);

	/**
	 * get descriptor by given resource class and descriptor class
	 * 
	 * @param resourceClass
	 * @param descriptorClass
	 * @return
	 */
	<T extends IResourceDescriptor> T get(Class<?> resourceClass, Class<T> descriptorClass);

	/**
	 * put resource class and its descriptor pair into repository
	 * 
	 * @param resourceClass
	 * @param descriptor
	 * @return
	 */
	IResourceDescriptor put(Class<?> resourceClass, IResourceDescriptor descriptor);

	/**
	 * get all descriptors
	 * 
	 * @return
	 */
	Collection<IResourceDescriptor> getDescriptors();

	/**
	 * get all descriptors by given class
	 * 
	 * @param descriptorClass
	 * @return
	 */
	<T> Collection<T> getDescriptors(Class<T> descriptorClass);
}
