/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.providers;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.IBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.AbstractOValBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.validators.oval.OValBeanValidator184;

/**
 * OVal bean validator provider
 * 
 * @author brad.wu
 */
public class OValBeanValidatorProvider implements IOperatorProvider {
	private Class<? extends AbstractOValBeanValidator> validatorClass = null;

	/**
	 * @return the validatorClass
	 */
	public Class<? extends AbstractOValBeanValidator> getValidatorClass() {
		return this.validatorClass == null ? OValBeanValidator184.class : this.validatorClass;
	}

	/**
	 * @param validatorClass
	 *            the validatorClass to set
	 */
	public void setValidatorClass(Class<? extends AbstractOValBeanValidator> validatorClass) {
		this.validatorClass = validatorClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IBeanValidator createDefaultOperator(IResourceDescriptor descriptor) {
		IBeanValidator validator;
		try {
			validator = this.getValidatorClass().newInstance();
		} catch (InstantiationException e) {
			throw new ResourceException("Failed to create validator of [" + descriptor + "]");
		} catch (IllegalAccessException e) {
			throw new ResourceException("Failed to create validator of [" + descriptor + "]");
		}
		validator.setResourceDescriptor(descriptor);
		return validator;
	}
}
