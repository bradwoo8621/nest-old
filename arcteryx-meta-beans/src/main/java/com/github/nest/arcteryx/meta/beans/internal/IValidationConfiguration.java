/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

/**
 * validation configuration
 * 
 * @author brad.wu
 */
public interface IValidationConfiguration {
	/**
	 * get real configuration
	 * 
	 * @return
	 */
	<T> T getRealConfiguration();
}
