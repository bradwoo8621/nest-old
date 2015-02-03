/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import com.github.nest.arcteryx.meta.IConfigurationInitializer;

/**
 * persistent configuration initializer
 * 
 * @author brad.wu
 */
public interface IPersistentConfigurationInitializer extends IConfigurationInitializer<IPersistentConfiguration> {
	String KEY = "configuration.persistent";
}
