/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.providers;

import com.github.nest.arcteryx.meta.IOperatorProvider;
import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanValidator;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateBeanValidator;

/**
 * hibernate bean validator provider
 * 
 * @author brad.wu
 */
public class HibernateBeanValidatorProvider implements IOperatorProvider {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IOperatorProvider#createDefaultOperator(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public IBeanValidator createDefaultOperator(IResourceDescriptor descriptor) {
		HibernateBeanValidator validator = new HibernateBeanValidator();
		validator.setResourceDescriptor(descriptor);
		return validator;
	}
}
