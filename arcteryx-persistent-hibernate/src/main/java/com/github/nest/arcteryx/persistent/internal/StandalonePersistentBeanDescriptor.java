/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor;

/**
 * standalone persistent bean descriptor
 * 
 * @author brad.wu
 */
@SuppressWarnings("unchecked")
public class StandalonePersistentBeanDescriptor extends AbstractPersistentBeanDescriptor implements
		IStandalonePersistentBeanDescriptor {
	private static final long serialVersionUID = -5413740700100061607L;
	private String tableName = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getTableName()
	 */
	@Override
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
