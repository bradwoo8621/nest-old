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
	<T extends IResourceDescriptor> T get(Object resource);

	/**
	 * get descriptor by given resource class
	 * 
	 * @param resourceClass
	 * @return
	 */
	<T extends IResourceDescriptor> T get(Class<?> resourceClass);

	/**
	 * put resource class and its descriptor pair into repository
	 * 
	 * @param resourceClass
	 * @param descriptor
	 * @return
	 */
	IResourceDescriptor put(Class<? extends IResourceDescriptor> resourceClass, IResourceDescriptor descriptor);

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
