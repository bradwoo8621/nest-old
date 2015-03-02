/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.IOneToOneReversePersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;

/**
 * one-to-one reverse parent persistent column
 * 
 * @author brad.wu
 */
public class OneToOneReversePersistentColumn extends AbstractPersistentColumn implements
		IOneToOneReversePersistentColumn {
	private static final long serialVersionUID = 1304524763168277165L;

	private Class<?> beanClass = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IOneToOneReversePersistentColumn#getParentBean()
	 */
	@Override
	public IPersistentBeanDescriptor getParentBean() {
		return this.getPropertyDescriptor().getBeanDescriptor().getContext().get(beanClass);
	}

	/**
	 * set parent bean class
	 * 
	 * @param beanClass
	 */
	public void setParentBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}
}
