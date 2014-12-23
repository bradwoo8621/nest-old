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
	 * triggered after a descriptor put into context.
	 * 
	 * @param resourceClass
	 * @param descriptor
	 */
	void postPutIntoContext(Class<?> resourceClass, IResourceDescriptor descriptor);

	/**
	 * decorate descriptor. triggered when return to caller after get descriptor
	 * from context.
	 * 
	 * @param descriptor
	 * @return
	 */
	IResourceDescriptor decorateDescriptor(IResourceDescriptor descriptor);

}
