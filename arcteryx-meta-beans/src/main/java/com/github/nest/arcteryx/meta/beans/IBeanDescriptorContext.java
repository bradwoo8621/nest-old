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
	 * do when after context initialized, this method must be called manually or
	 * declared with <code>init-method</code> in spring context
	 */
	void afterBeanContextInitialized();

	/**
	 * get validation configuration initialzier
	 * 
	 * @return
	 */
	IValidationConfigurationInitializer getValidationConfigurationInitializer();

	/**
	 * get validation configuration
	 * 
	 * @return
	 */
	IValidationConfiguration getValidationConfiguration();
}
