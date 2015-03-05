/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
	// key: propertyName; value: joined table name
	private Map<String, String> joinedProperties = null;
	// key: joined table name; value: primary key column name
	private Map<String, String> joinedTablePrimaryKeyColumnNames = null;

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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getJoinedTableNames()
	 */
	@Override
	public String[] getJoinedTableNames() {
		if (this.joinedProperties == null || this.joinedProperties.size() == 0) {
			return new String[] {};
		}
		List<String> list = new LinkedList<String>();
		for (String tableName : this.joinedProperties.values()) {
			if (!list.contains(tableName)) {
				list.add(tableName);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#isJoined(java.lang.String)
	 */
	@Override
	public boolean isJoined(String propertyName) {
		return StringUtils.isNotBlank(this.getJoinedTableName(propertyName));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getJoinedTableName(java.lang.String)
	 */
	@Override
	public String getJoinedTableName(String propertyName) {
		return this.joinedProperties == null ? null : this.joinedProperties.get(propertyName);
	}

	/**
	 * set joined properties
	 * 
	 * @param joinedProperties
	 */
	public void setJoinedProperties(Map<String, String> joinedProperties) {
		this.joinedProperties = joinedProperties;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getJoinedTablePrimaryKeyColumnName(java.lang.String)
	 */
	@Override
	public String getJoinedTablePrimaryKeyColumnName(String joinedTableName) {
		return this.joinedTablePrimaryKeyColumnNames == null ? null : this.joinedTablePrimaryKeyColumnNames
				.get(joinedTableName);
	}

	/**
	 * set joined table primary key column names
	 * 
	 * @param joinedTablePrimaryKeyColumnNames
	 */
	public void setJoinedTablePrimaryKeyColumnNames(Map<String, String> joinedTablePrimaryKeyColumnNames) {
		this.joinedTablePrimaryKeyColumnNames = joinedTablePrimaryKeyColumnNames;
	}
}
