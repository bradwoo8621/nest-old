/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
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
	private String foreignKeyColumnName = null;
	private String foreignKeyPropertyName = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn#getReferencedBean()
	 */
	@Override
	public IBeanDescriptor getReferencedBean() {
		IResourceDescriptorContext context = this.getPropertyDescriptor().getBeanDescriptor().getContext();
		return context.get(beanClass);
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
				Collection<IPersistentBeanPropertyDescriptor> properties = persistentBean.getPersistentProperties();
				for (IPersistentBeanPropertyDescriptor property : properties) {
					IPersistentColumn column = property.getPersistentColumn();
					if (column instanceof IPrimitivePersistentColumn) {
						if (((IPrimitivePersistentColumn) column).isPrimaryKey()) {
							return property.getName();
						}
					}
				}
				throw new ResourceException("Primary key not found in persistent bean [" + bean.getBeanClass() + "].");
			} else {
				throw new ResourceException("Foreign key property name not set on ["
						+ this.getPropertyDescriptor().getBeanDescriptor().getBeanClass() + "#"
						+ this.getPropertyDescriptor().getName() + "].");
			}
		}
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
}
