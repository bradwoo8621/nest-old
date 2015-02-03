/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptorContext;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptorContext;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;

/**
 * persistent bean descriptor context
 * 
 * @author brad.wu
 */
public class PersistentBeanDescriptorContext extends BeanDescriptorContext implements IPersistentBeanDescriptorContext {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanDescriptorContext#getPersistentConfiguration()
	 */
	@Override
	public IPersistentConfiguration getPersistentConfiguration() {
		return this.getInitializedData(IPersistentConfigurationInitializer.KEY);
	}

	/**
	 * set persistent configuration initializer
	 * 
	 * @param persistentConfigurationInitializer
	 */
	public void setPersistentConfigurationInitializer(
			IPersistentConfigurationInitializer persistentConfigurationInitializer) {
		this.addConfigurationInitializer(persistentConfigurationInitializer);
	}
}
