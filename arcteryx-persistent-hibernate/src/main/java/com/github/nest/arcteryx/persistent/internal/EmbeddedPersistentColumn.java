/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.ResourceDescriptorContextRepository;
import com.github.nest.arcteryx.persistent.IEmbeddablePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IEmbeddedPersistentColumn;

/**
 * embedded persistent column
 * 
 * @author brad.wu
 */
public class EmbeddedPersistentColumn extends AbstractPersistentColumn implements IEmbeddedPersistentColumn {
	private static final long serialVersionUID = 967309236880750381L;

	private Class<?> beanClass = null;
	private String referencedBeanContextName = null;
	// key: property name, can be concatenated by point;
	// value: new column name
	private Map<String, String> overriddenNames = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IEmbeddedPersistentColumn#getEmbeddedBean()
	 */
	@Override
	public IEmbeddablePersistentBeanDescriptor getEmbeddedBean() {
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
	 * set embedded bean name
	 * 
	 * @param beanName
	 */
	public void setEmbeddedBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IEmbeddedPersistentColumn#getReferencedBeanContextName()
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
	 * @see com.github.nest.arcteryx.persistent.IEmbeddedPersistentColumn#getOverriddenColumnName(java.lang.String)
	 */
	@Override
	public String getOverriddenColumnName(String propertyName) {
		return this.overriddenNames == null ? null : this.overriddenNames.get(propertyName);
	}

	/**
	 * set overridden column names
	 * 
	 * @param overriddenColumnNames
	 */
	public void setOverriddenColumnNames(Map<String, String> overriddenColumnNames) {
		this.overriddenNames = overriddenColumnNames;
	}

	/**
	 * add overridden column name
	 * 
	 * @param propertyName
	 * @param newColumnName
	 */
	public void addOverriddenColumnName(String propertyName, String newColumnName) {
		if (this.overriddenNames == null) {
			synchronized (this) {
				if (this.overriddenNames == null) {
					this.overriddenNames = new HashMap<String, String>();
				}
			}
		}
		this.overriddenNames.put(propertyName, newColumnName);
	}
}
