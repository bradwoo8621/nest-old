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
	 * get descriptor by given resource, or its ancestors
	 * 
	 * @param resource
	 * @return
	 */
	<T extends IResourceDescriptor> T getRecursive(Object resource);

	/**
	 * get descriptor by given resource class
	 * 
	 * @param resourceClass
	 * @return
	 */
	<T extends IResourceDescriptor> T get(Class<?> resourceClass);

	/**
	 * get descriptor by given resource class, or its ancestors
	 * 
	 * @param resourceClass
	 * @return
	 */
	<T extends IResourceDescriptor> T getRecursive(Class<?> resourceClass);

	/**
	 * put resource descriptor pair into repository
	 * 
	 * @param descriptor
	 *            old in context
	 * 
	 * @return
	 */
	IResourceDescriptor register(IResourceDescriptor descriptor);

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

	/**
	 * get operator provider registry, context level
	 * 
	 * @return
	 */
	IOperatorProviderRegistry getOperatorProviderRegistry();

	/**
	 * register
	 * 
	 * @param provider
	 * @param code
	 */
	void registerOperatorProvider(IOperatorProvider provider, String code);
}
