/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.ResourceDescriptorContextRepository;
import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.persistent.ICollectionParameter;
import com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;

/**
 * many-to-many persistent column
 * 
 * @author brad.wu
 */
public class ManyToManyPersistentColumn extends AbstractPersistentColumn implements IManyToManyPersistentColumn {
	private static final long serialVersionUID = 2401613291731439553L;

	private Class<?> referencedBeanClass = null;
	private String referencedBeanContextName = null;
	private String intermediateTableName = null;
	private String foreignKeyColumnNameToMe = null;
	private String foreignKeyColumnNameToRefer = null;
	private String foreignKeyPropertyNameToRefer = null;
	private ICollectionParameter collectionParameter = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getReferencedBean()
	 */
	@Override
	public IBeanDescriptor getReferencedBean() {
		if (StringUtils.isBlank(getReferencedBeanContextName())) {
			IResourceDescriptorContext context = this.getPropertyDescriptor().getBeanDescriptor().getContext();
			return context.get(referencedBeanClass);
		} else {
			IResourceDescriptorContext context = ResourceDescriptorContextRepository.getContext(this
					.getReferencedBeanContextName());
			return context.get(referencedBeanClass);
		}
	}

	/**
	 * @param referencedBeanClass
	 *            the referencedBeanClass to set
	 */
	public void setReferencedBeanClass(Class<?> referencedBeanClass) {
		this.referencedBeanClass = referencedBeanClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getReferencedBeanContextName()
	 */
	@Override
	public String getReferencedBeanContextName() {
		return this.referencedBeanContextName;
	}

	/**
	 * @param referencedBeanContextName
	 *            the referencedBeanContextName to set
	 */
	public void setReferencedBeanContextName(String referencedBeanContextName) {
		this.referencedBeanContextName = referencedBeanContextName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getIntermediateTableName()
	 */
	@Override
	public String getIntermediateTableName() {
		return this.intermediateTableName;
	}

	/**
	 * @param intermediateTableName
	 *            the intermediateTableName to set
	 */
	public void setIntermediateTableName(String intermediateTableName) {
		this.intermediateTableName = intermediateTableName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getForeignKeyColumnNameToMe()
	 */
	@Override
	public String getForeignKeyColumnNameToMe() {
		return this.foreignKeyColumnNameToMe;
	}

	/**
	 * @param foreignKeyColumnNameToMe
	 *            the foreignKeyColumnNameToMe to set
	 */
	public void setForeignKeyColumnNameToMe(String foreignKeyColumnNameToMe) {
		this.foreignKeyColumnNameToMe = foreignKeyColumnNameToMe;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getForeignKeyColumnNameToRefer()
	 */
	@Override
	public String getForeignKeyColumnNameToRefer() {
		return this.foreignKeyColumnNameToRefer;
	}

	/**
	 * @param foreignKeyColumnNameToRefer
	 *            the foreignKeyColumnNameToRefer to set
	 */
	public void setForeignKeyColumnNameToRefer(String foreignKeyColumnNameToRefer) {
		this.foreignKeyColumnNameToRefer = foreignKeyColumnNameToRefer;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getForeignKeyPropertyNameToRefer()
	 */
	@Override
	public String getForeignKeyPropertyNameToRefer() {
		if (StringUtils.isNotBlank(this.foreignKeyPropertyNameToRefer)) {
			return this.foreignKeyPropertyNameToRefer;
		} else {
			IBeanDescriptor bean = this.getReferencedBean();
			if (bean instanceof IPersistentBeanDescriptor) {
				// if it is a persistent bean, many to one foreign key must be
				// referred to its primary key.
				IPersistentBeanDescriptor persistentBean = (IPersistentBeanDescriptor) bean;
				IPersistentBeanPropertyDescriptor primaryKey = findPrimaryKeyProperty(persistentBean);
				if (primaryKey == null) {
					throw new ResourceException("Primary key not found in persistent bean [" + bean.getBeanClass()
							+ "].");
				} else {
					return primaryKey.getName();
				}
			} else {
				throw new ResourceException("Foreign key property name not set on ["
						+ this.getPropertyDescriptor().getBeanDescriptor().getBeanClass() + "#"
						+ this.getPropertyDescriptor().getName() + "].");
			}
		}
	}

	/**
	 * find primary key property of given bean
	 * 
	 * @param bean
	 * @return
	 */
	protected IPersistentBeanPropertyDescriptor findPrimaryKeyProperty(IPersistentBeanDescriptor bean) {
		Collection<IPersistentBeanPropertyDescriptor> properties = bean.getPersistentProperties();
		for (IPersistentBeanPropertyDescriptor property : properties) {
			IPersistentColumn column = property.getPersistentColumn();
			if (column instanceof IPrimitivePersistentColumn) {
				if (((IPrimitivePersistentColumn) column).isPrimaryKey()) {
					return property;
				}
			}
		}
		return null;
	}

	/**
	 * @param foreignKeyPropertyNameToRefer
	 *            the foreignKeyPropertyNameToRefer to set
	 */
	public void setForeignKeyPropertyNameToRefer(String foreignKeyPropertyNameToRefer) {
		this.foreignKeyPropertyNameToRefer = foreignKeyPropertyNameToRefer;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#isInSameContext()
	 */
	@Override
	public boolean isInSameContext() {
		return StringUtils.equals(this.getReferencedBeanContextName(), this.getPropertyDescriptor().getBeanDescriptor()
				.getName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn#getCollectionParameter()
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
