/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * resource descriptor repository interface
 * 
 * @author brad.wu
 */
public interface IResourceDescriptorRepository {
	/**
	 * get descriptor by given resource instance
	 * 
	 * @param resource
	 * @return
	 */
	IResourceDescriptor get(Object resource);

	/**
	 * get descriptor by given resource class
	 * 
	 * @param resourceClass
	 * @return
	 */
	IResourceDescriptor get(Class<?> resourceClass);

	/**
	 * put resource class and its descriptor pair into repository
	 * 
	 * @param resourceClass
	 * @param descriptor
	 * @return
	 */
	IResourceDescriptor put(Class<?> resourceClass, IResourceDescriptor descriptor);
}
