/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.AbstractResourceOperator;
import com.github.nest.arcteryx.meta.IResourceDescriptor;

/**
 * abstract bean operator
 * 
 * @author brad.wu
 */
public abstract class AbstractBeanOperator extends AbstractResourceOperator implements IBeanOperator {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanOperator#getBeanDescriptor()
	 */
	@Override
	public IBeanDescriptor getBeanDescriptor() {
		return (IBeanDescriptor) getResourceDescriptor();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.AbstractResourceOperator#setResourceDescriptor(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void setResourceDescriptor(IResourceDescriptor resourceDescriptor) {
		assert resourceDescriptor instanceof IBeanDescriptor : "Resource descriptor must be an instanceof "
				+ IBeanDescriptor.class;
	
		super.setResourceDescriptor(resourceDescriptor);
	}
}
