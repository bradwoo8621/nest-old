/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.persistent.ICollectionParameter;
import com.github.nest.arcteryx.persistent.IOneToManyPersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;

/**
 * one-to-many persistent column
 * 
 * @author brad.wu
 */
public class OneToManyPersistentColumn extends AbstractPersistentColumn implements IOneToManyPersistentColumn {
	private static final long serialVersionUID = 2833048471416202346L;

	private Class<?> beanClass = null;
	private String foreignKeyPropertyName = null;
	private String foreignKeyColumnName = null;
	private ICollectionParameter collectionParameter = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IOneToManyPersistentColumn#getSubordinateBean()
	 */
	@Override
	public IPersistentBeanDescriptor getSubordinateBean() {
		return this.getPropertyDescriptor().getBeanDescriptor().getContext().get(beanClass);
	}

	/**
	 * set subordinate bean class
	 * 
	 * @param beanClass
	 */
	public void setSubordinateBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IOneToManyPersistentColumn#getForeignKeyPropertyName()
	 */
	@Override
	public String getForeignKeyPropertyName() {
		return this.foreignKeyPropertyName;
	}

	/**
	 * @param foreignKeyPropertyName
	 *            the foreignKeyPropertyName to set
	 */
	public void setForeignKeyPropertyName(String foreignKeyPropertyName) {
		this.foreignKeyPropertyName = foreignKeyPropertyName;
	}

	/**
	 * if foreign key column name is not set, use foreign key property name to
	 * find the column name
	 * 
	 * @see com.github.nest.arcteryx.persistent.IOneToManyPersistentColumn#getForeignKeyColumnName()
	 */
	@Override
	public String getForeignKeyColumnName() {
		if (StringUtils.isNotBlank(this.foreignKeyColumnName)) {
			return this.foreignKeyColumnName;
		} else {
			IPersistentBeanDescriptor beanDescriptor = this.getSubordinateBean();
			IPersistentBeanPropertyDescriptor propertyDescriptor = beanDescriptor.getProperty(this
					.getForeignKeyPropertyName());
			IPrimitivePersistentColumn column = (IPrimitivePersistentColumn) propertyDescriptor.getPersistentColumn();
			return column.getName();
		}
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
	 * @see com.github.nest.arcteryx.persistent.IOneToManyPersistentColumn#getCollectionParameter()
	 */
	@Override
	public ICollectionParameter getCollectionParameter() {
		return this.collectionParameter;
	}

	/**
	 * @param collectionParameter
	 *            the collectionParameter to set
	 */
	public void setCollectionParameter(ICollectionParameter collectionParameter) {
		this.collectionParameter = collectionParameter;
	}
}
