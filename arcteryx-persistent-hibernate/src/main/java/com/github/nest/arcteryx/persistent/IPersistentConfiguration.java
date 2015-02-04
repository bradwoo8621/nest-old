/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * persistent configuration
 * 
 * @author brad.wu
 */
public interface IPersistentConfiguration {
	/**
	 * get real configuration
	 * 
	 * @return
	 */
	<T> T getRealConfiguration();
}
