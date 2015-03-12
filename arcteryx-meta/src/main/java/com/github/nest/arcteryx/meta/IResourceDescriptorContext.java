/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * resource descriptor repository interface.<br>
 * there are 2 ways to register a descriptor into context, see
 * {@linkplain #register(IResourceDescriptor)} and
 * {@linkplain #register(IResourceDescriptor, String)}. difference between the 2
 * ways is, if register via {@linkplain #register(IResourceDescriptor)}, the
 * descriptor will be treaded as default descriptor of a class, which means when
 * call any method to get descriptor of a class or an object, the returned
 * descriptor is registered by {@linkplain #register(IResourceDescriptor)}. the
 * only way to find the descriptor with identity is {@linkplain #get(String)}.
 * and when a descriptor is registered via
 * {@linkplain #register(IResourceDescriptor)}, no identity will be set. so the
 * descriptor will not be found via {@linkplain #get(String)} unless register it
 * manually via {@linkplain #register(IResourceDescriptor, String)}.
 * 
 * @author brad.wu
 */
public interface IResourceDescriptorContext {
	/**
	 * get name of context. if only one context in environment, null is allowed.
	 * 
	 * @return
	 */
	String getName();

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
	 * get descriptor by given identity
	 * 
	 * @param descriptorIdentity
	 * @return
	 */
	<T extends IResourceDescriptor> T get(String descriptorIdentity);

	/**
	 * register descriptor by give identity
	 * 
	 * @param descriptor
	 * @param identity
	 * @return
	 */
	IResourceDescriptor register(IResourceDescriptor descriptor, String identity);

	/**
	 * get all identities of registered descriptors
	 * 
	 * @return
	 */
	Set<String> getIdentitiesOfDescriptor();

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
