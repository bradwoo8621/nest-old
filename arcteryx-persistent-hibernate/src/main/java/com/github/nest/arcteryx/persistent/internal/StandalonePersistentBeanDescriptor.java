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

	// the following fields are used to describe a bean which is a sub-class
	// extends from another bean.
	private Class<?> extendsFromBeanClass = null;
	private String foreignKeyColumnName = null;
	private String discriminatorValue = null;
	// to describe the bean which will be extended by other classes, the
	// discriminator column is used to identify the sub-class
	private String discriminatorColumnName = null;

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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getExtendsFrom()
	 */
	@Override
	public IStandalonePersistentBeanDescriptor getExtendsFrom() {
		return (IStandalonePersistentBeanDescriptor) (this.extendsFromBeanClass == null ? null : this.getContext().get(
				this.extendsFromBeanClass));
	}

	/**
	 * @param extendsFromBeanClass
	 *            the extendsFromBeanClass to set
	 */
	public void setExtendsFromBeanClass(Class<?> extendsFromBeanClass) {
		this.extendsFromBeanClass = extendsFromBeanClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getDiscriminatorValue()
	 */
	@Override
	public String getDiscriminatorValue() {
		return this.discriminatorValue;
	}

	/**
	 * @param discriminatorValue
	 *            the discriminatorValue to set
	 */
	public void setDiscriminatorValue(String discriminatorValue) {
		this.discriminatorValue = discriminatorValue;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getForeignKeyColumnName()
	 */
	@Override
	public String getForeignKeyColumnName() {
		return this.foreignKeyColumnName;
	}

	/**
	 * @param foreignKeyColumnName
	 *            the foreignKeyColumnName to set
	 */
	public void setForeignKeyColumnName(String foreignKeyColumnName) {
		this.foreignKeyColumnName = foreignKeyColumnName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor#getDiscriminatorColumnName()
	 */
	@Override
	public String getDiscriminatorColumnName() {
		return this.discriminatorColumnName;
	}

	/**
	 * @param discriminatorColumnName
	 *            the discriminatorColumnName to set
	 */
	public void setDiscriminatorColumnName(String discriminatorColumnName) {
		this.discriminatorColumnName = discriminatorColumnName;
	}
}
