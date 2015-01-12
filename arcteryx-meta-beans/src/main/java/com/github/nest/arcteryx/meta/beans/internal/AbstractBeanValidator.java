/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanValidator;

/**
 * bean validator
 * 
 * @author brad.wu
 */
public abstract class AbstractBeanValidator extends AbstractStaticCodeBeanOperator implements IBeanValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
	}
}
