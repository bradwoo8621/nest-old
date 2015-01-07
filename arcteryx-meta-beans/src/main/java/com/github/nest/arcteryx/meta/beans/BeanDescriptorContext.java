/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.ResourceDescriptorContext;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public class BeanDescriptorContext extends ResourceDescriptorContext {
	private String defaultValidatorVendor = null;

	/**
	 * @return the defaultValidatorVendor
	 */
	protected String getDefaultValidatorVendor() {
		return defaultValidatorVendor;
	}

	/**
	 * @param defaultValidatorVendor the defaultValidatorVendor to set
	 */
	protected void setDefaultValidatorVendor(String defaultValidatorVendor) {
		this.defaultValidatorVendor = defaultValidatorVendor;
	}
}
