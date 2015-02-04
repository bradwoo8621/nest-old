/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.IPersistentConfiguration;

/**
 * persistent configuration
 * 
 * @author brad.wu
 */
public class PersistentConfiguration implements IPersistentConfiguration {
	private Object configuration = null;

	public PersistentConfiguration(Object configuration) {
		this.configuration = configuration;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentConfiguration#getRealConfiguration()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getRealConfiguration() {
		return (T) this.configuration;
	}
}
