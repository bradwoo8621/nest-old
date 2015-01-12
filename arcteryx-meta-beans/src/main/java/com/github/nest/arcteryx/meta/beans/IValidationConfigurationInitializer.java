/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;

/**
 * validation configuration initializer
 * 
 * @author brad.wu
 */
public interface IValidationConfigurationInitializer {
	/**
	 * initialize validation configuration for given context
	 * 
	 * @param context
	 * @return 
	 */
	IValidationConfiguration initialize(IBeanDescriptorContext context);
}
