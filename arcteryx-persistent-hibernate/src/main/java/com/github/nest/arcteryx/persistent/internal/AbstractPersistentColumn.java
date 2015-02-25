/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;

/**
 * abstract persistent column
 * 
 * @author brad.wu
 */
public abstract class AbstractPersistentColumn implements IPersistentColumn {
	private static final long serialVersionUID = 4099354887734773455L;

	private IPersistentBeanPropertyDescriptor propertyDescriptor = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentColumn#getPropertyDescriptor()
	 */
	@Override
	public IPersistentBeanPropertyDescriptor getPropertyDescriptor() {
		return this.propertyDescriptor;
	}

	/**
	 * @param propertyDescriptor
	 *            the propertyDescriptor to set
	 */
	public void setPropertyDescriptor(IPersistentBeanPropertyDescriptor propertyDescriptor) {
		this.propertyDescriptor = propertyDescriptor;
	}
}
