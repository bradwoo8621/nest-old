/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IConfigurationInitializer;
import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;

/**
 * validation configuration initializer
 * 
 * @author brad.wu
 */
public interface IValidationConfigurationInitializer extends
		IConfigurationInitializer<IValidationConfiguration, IBeanDescriptorContext> {
	String KEY = "configuration.validation";
}
