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
import com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;

/**
 * reference persistent column(many-to-one).
 * 
 * @author brad.wu
 */
public class ManyToOnePersistentColumn extends AbstractPersistentColumn implements IManyToOnePersistentColumn {
	private static final long serialVersionUID = 7893122935676364181L;

	private Class<?> beanClass = null;
	private String referencedBeanContextName = null;
	private String foreignKeyColumnName = null;
	private String foreignKeyPropertyName = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn#getReferencedBean()
	 */
	@Override
	public IBeanDescriptor getReferencedBean() {
		if (StringUtils.isBlank(getReferencedBeanContextName())) {
			IResourceDescriptorContext context = this.getPropertyDescriptor().getBeanDescriptor().getContext();
			return context.get(beanClass);
		} else {
			IResourceDescriptorContext context = ResourceDescriptorContextRepository.getContext(this
					.getReferencedBeanContextName());
			return context.get(beanClass);
		}
	}

	/**
	 * set referenced bean class
	 * 
	 * @param beanClass
	 */
	public void setReferencedBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn#getReferencedBeanContextName()
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
	 * @see com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn#getForeignKeyColumnName()
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
	 * @see com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn#getForeignKeyPropertyName()
	 */
	@Override
	public String getForeignKeyPropertyName() {
		if (StringUtils.isNotBlank(this.foreignKeyPropertyName)) {
			return this.foreignKeyPropertyName;
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
	 * call me when the referenced bean is not a persistent bean
	 * 
	 * @param foreignKeyPropertyName
	 *            the foreignKeyPropertyName to set
	 */
	public void setForeignKeyPropertyName(String foreignKeyPropertyName) {
		this.foreignKeyPropertyName = foreignKeyPropertyName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn#isInSameContext()
	 */
	@Override
	public boolean isInSameContext() {
		return StringUtils.isBlank(this.getReferencedBeanContextName())
				|| StringUtils.equals(this.getReferencedBeanContextName(), this.getPropertyDescriptor()
						.getBeanDescriptor().getContext().getName());
	}
}
