/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators;

import com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration;

/**
 * validation configuration
 * 
 * @author brad.wu
 */
public class ValidationConfiguration implements IValidationConfiguration {
	private Object configuration = null;

	public ValidationConfiguration(Object configuration) {
		this.configuration = configuration;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.IValidationConfiguration#getRealConfiguration()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getRealConfiguration() {
		return (T) this.configuration;
	}
}
