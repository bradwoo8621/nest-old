/**
 * 
 */
package com.github.nest.goose.internal;

import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.IBeanIdentity;
import com.github.nest.arcteryx.meta.beans.IBeanIdentityExtracter;
import com.github.nest.arcteryx.meta.beans.internal.AbstractBeanOperator;
import com.github.nest.goose.ICodeBaseBean;

/**
 * code extracter
 * 
 * @author brad.wu
 */
public class CodeExtracter extends AbstractBeanOperator implements IBeanIdentityExtracter {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanIdentityExtracter#extract(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IBeanIdentity> T extract(Object bean) {
		if (bean == null) {
			return null;
		}

		if (bean instanceof ICodeBaseBean) {
			return (T) new Code(((ICodeBaseBean) bean).getCode());
		} else {
			throw new ResourceException("Bean [" + bean + "] must be an instance of [" + ICodeBaseBean.class + "].");
		}
	}
}
