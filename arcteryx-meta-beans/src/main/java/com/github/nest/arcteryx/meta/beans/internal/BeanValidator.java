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
public class BeanValidator extends AbstractStaticCodeBeanOperator implements IBeanValidator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object)
	 */
	@Override
	public <T> T validate(Object resource) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.String[])
	 */
	@Override
	public <T> T validate(Object resource, String... profiles) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanValidator#validate(java.lang.Object,
	 *      java.lang.Class[])
	 */
	@Override
	public <T> T validate(Object resource, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}

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
