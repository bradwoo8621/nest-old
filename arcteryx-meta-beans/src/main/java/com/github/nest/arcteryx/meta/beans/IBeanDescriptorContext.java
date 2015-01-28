/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public interface IBeanDescriptorContext extends IResourceDescriptorContext {
	/**
	 * get validation configuration
	 * 
	 * @return
	 */
	IValidationConfiguration getValidationConfiguration();
}
