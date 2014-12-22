/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * Resource descriptor context interceptor
 * 
 * @author brad.wu
 */
public interface IResouceDescriptorContextInterceptor {
	/**
	 * triggered after a descriptor put into context
	 * 
	 * @param resourceClass
	 * @param descriptor
	 */
	void postPutIntoContext(Class<?> resourceClass, IResourceDescriptor descriptor);
}
