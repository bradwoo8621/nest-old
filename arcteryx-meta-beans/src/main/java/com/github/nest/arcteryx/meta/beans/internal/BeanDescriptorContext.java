/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IValidationConfigurationInitializer;
import com.github.nest.arcteryx.meta.internal.ResourceDescriptorContext;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public class BeanDescriptorContext extends ResourceDescriptorContext implements IBeanDescriptorContext {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext#getValidationConfiguration()
	 */
	@Override
	public IValidationConfiguration getValidationConfiguration() {
		return this.getInitializedData(IValidationConfigurationInitializer.KEY);
	}

	/**
	 * @param validationConfigurationInitializer
	 *            the validationConfigurationInitializer to set
	 */
	public void setValidationConfigurationInitializer(
			IValidationConfigurationInitializer validationConfigurationInitializer) {
		this.addConfigurationInitializer(validationConfigurationInitializer);
	}
}
