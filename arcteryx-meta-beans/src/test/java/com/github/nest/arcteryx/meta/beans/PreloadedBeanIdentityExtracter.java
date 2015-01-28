/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.beans.internal.AbstractStaticCodeBeanOperator;

/**
 * @author brad.wu
 *
 */
public class PreloadedBeanIdentityExtracter extends AbstractStaticCodeBeanOperator implements IBeanIdentityExtracter {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanIdentityExtracter#extract(java.lang.Object)
	 */
	@Override
	public IBeanIdentity extract(Object bean) {
		assert bean instanceof PreloadedBean;

		BeanID id = new BeanID();
		id.setId(((PreloadedBean) bean).getId());
		return id;
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
