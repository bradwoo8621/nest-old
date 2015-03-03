/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.IOneToManyReversePersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;

/**
 * one-to-many reverse persistent column
 * 
 * @author brad.wu
 */
public class OneToManyReversePersistentColumn extends AbstractPersistentColumn implements
		IOneToManyReversePersistentColumn {
	private static final long serialVersionUID = 4793752115833889742L;

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
