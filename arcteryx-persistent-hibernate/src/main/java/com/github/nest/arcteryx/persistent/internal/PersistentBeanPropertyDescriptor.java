/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.meta.beans.internal.BeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;

/**
 * persistent bean property descriptor
 * 
 * @author brad.wu
 */
@SuppressWarnings("unchecked")
public class PersistentBeanPropertyDescriptor extends BeanPropertyDescriptor implements
		IPersistentBeanPropertyDescriptor {
	private static final long serialVersionUID = -580929485779678370L;

	private IPersistentColumn persistentColumn = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor#getPersistentColumn()
	 */
	@Override
	public IPersistentColumn getPersistentColumn() {
		return this.persistentColumn;
	}

	/**
	 * @param persistentColumn
	 *            the persistentColumn to set
	 */
	public void setPersistentColumn(IPersistentColumn persistentColumn) {
		this.persistentColumn = persistentColumn;
	}
}
