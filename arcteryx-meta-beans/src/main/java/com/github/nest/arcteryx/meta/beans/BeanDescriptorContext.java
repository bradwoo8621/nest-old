/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResouceDescriptorContextInterceptor;
import com.github.nest.arcteryx.meta.ResourceDescriptorContext;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public class BeanDescriptorContext extends ResourceDescriptorContext {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.ResourceDescriptorContext#getContextInterceptor()
	 */
	@Override
	public IResouceDescriptorContextInterceptor getContextInterceptor() {
		IResouceDescriptorContextInterceptor interceptor = super.getContextInterceptor();
		if (interceptor == null) {
			this.setContextInterceptor(createContextInterceptor());
		}
		return super.getContextInterceptor();
	}

	/**
	 * create context interceptor
	 * 
	 * @return
	 */
	protected BeanDescriptorContextInterceptor createContextInterceptor() {
		return new BeanDescriptorContextInterceptor();
	}
}
