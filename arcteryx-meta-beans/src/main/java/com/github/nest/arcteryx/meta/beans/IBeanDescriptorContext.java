/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public interface IBeanDescriptorContext extends IResourceDescriptorContext {
	/**
	 * get operator provider
	 * 
	 * @return
	 */
	IBeanOperatorProvider getOperatorProvider();
}
