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
	 * put resource descriptor pair into repository
	 * 
	 * @param descriptor
	 *            old in context
	 * 
	 * @return
	 */
	IResourceDescriptor put(IResourceDescriptor descriptor);

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
	 * get default operator provider
	 * 
	 * @return
	 */
	<T extends IDefaultOperatorProviderRegistry> T getDefaultOperatorProvider();
}
