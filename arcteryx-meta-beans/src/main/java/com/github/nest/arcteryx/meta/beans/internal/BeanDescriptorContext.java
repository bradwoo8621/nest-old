/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanOperatorProvider;
import com.github.nest.arcteryx.meta.internal.ResourceDescriptorContext;

/**
 * bean descriptor context
 * 
 * @author brad.wu
 */
public class BeanDescriptorContext extends ResourceDescriptorContext implements IBeanDescriptorContext {
	private IBeanOperatorProvider operatorProvider = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext#getOperatorProvider()
	 */
	@Override
	public IBeanOperatorProvider getOperatorProvider() {
		return this.operatorProvider;
	}

	/**
	 * @param operatorProvider
	 *            the operatorProvider to set
	 */
	public void setOperatorProvider(IBeanOperatorProvider operatorProvider) {
		this.operatorProvider = operatorProvider;
	}
}
