/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.Collection;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IValidationConfigurationInitializer;
import com.github.nest.arcteryx.meta.internal.ResourceDescriptorContext;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public class BeanDescriptorContext extends ResourceDescriptorContext implements IBeanDescriptorContext {
	private IValidationConfigurationInitializer validationConfigurationInitializer = null;
	private IValidationConfiguration validationConfiguration = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext#afterBeanContextInitialized()
	 */
	@Override
	public void afterBeanContextInitialized() {
		this.validationConfiguration = initializeValidationConfiguration();
	}

	/**
	 * initialize validation configuration
	 * 
	 * @return
	 */
	protected IValidationConfiguration initializeValidationConfiguration() {
		return getValidationConfigurationInitializer().initialize(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext#getValidationConfiguration()
	 */
	@Override
	public IValidationConfiguration getValidationConfiguration() {
		return this.validationConfiguration;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext#getValidationConfigurationInitializer()
	 */
	@Override
	public IValidationConfigurationInitializer getValidationConfigurationInitializer() {
		return this.validationConfigurationInitializer;
	}

	/**
	 * @param validationConfigurationInitializer
	 *            the validationConfigurationInitializer to set
	 */
	public void setValidationConfigurationInitializer(
			IValidationConfigurationInitializer validationConfigurationInitializer) {
		this.validationConfigurationInitializer = validationConfigurationInitializer;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.ResourceDescriptorContext#register(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public IResourceDescriptor register(IResourceDescriptor descriptor) {
		assert descriptor instanceof IBeanDescriptor : "Descriptor must be an instance of " + IBeanDescriptor.class
				+ ".";
		return super.register(descriptor);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.ResourceDescriptorContext#setDescriptors(java.util.Collection)
	 */
	@Override
	public Collection<IResourceDescriptor> setDescriptors(Collection<IResourceDescriptor> descriptors) {
		assert descriptors != null : "Resource descriptor collection cannot be null.";
		for (IResourceDescriptor descriptor : descriptors) {
			assert descriptor instanceof IBeanDescriptor : "Descriptor must be an instance of " + IBeanDescriptor.class
					+ ".";
		}

		return super.setDescriptors(descriptors);
	}
}