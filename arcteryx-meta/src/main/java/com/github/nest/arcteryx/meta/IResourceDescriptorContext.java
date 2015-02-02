/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.Collection;
import java.util.List;

/**
 * resource descriptor repository interface
 * 
 * @author brad.wu
 */
public interface IResourceDescriptorContext {
	/**
	 * do when after context initialized, this method must be called manually or
	 * declared with <code>init-method</code> in spring context
	 */
	void afterContextInitialized();

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
	<T extends IResourceDescriptor> List<T> getRecursive(Object resource);

	/**
	 * get descriptor by given resource class
	 * 
	 * @param resourceClass
	 * @return
	 */
	<T extends IResourceDescriptor> T get(Class<?> resourceClass);

	/**
	 * get descriptor by given resource class, and its ancestors. super class
	 * has higher priority than interfaces.<br>
	 * eg.<br>
	 * <ul>
	 * <li>interface IInterface</li>
	 * <li>interface IInterface2</li>
	 * <li>class SuperClass implements IInterface, IInterface2</li>
	 * <li>interface IInterface3</li>
	 * <li>class AClass extends SuperClass implements IInterface3</li>
	 * </ul>
	 * the elements of return list is
	 * <ul>
	 * <li>AClass</li>
	 * <li>SuperClass</li>
	 * <li>IInterface2</li>
	 * <li>IInterface</li>
	 * <li>IInterface3</li>
	 * </ul>
	 * 
	 * 
	 * @param resourceClass
	 * @return
	 */
	<T extends IResourceDescriptor> List<T> getRecursive(Class<?> resourceClass);

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

	/**
	 * get initialized data
	 * 
	 * @param key
	 * @return
	 */
	<T> T getInitializedData(String key);
}
