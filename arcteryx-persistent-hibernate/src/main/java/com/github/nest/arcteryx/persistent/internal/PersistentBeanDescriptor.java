/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;

/**
 * persistent bean descriptor.<br>
 * Abandoned property is only applied to properties which declared in its
 * ancestors, not for {@linkplain #getDeclaredPersistentProperties()}.
 * 
 * @author brad.wu
 */
@SuppressWarnings("unchecked")
public class PersistentBeanDescriptor extends BeanDescriptor implements IPersistentBeanDescriptor {
	private static final long serialVersionUID = 4950944340850539340L;

	private String tableName = null;
	private Collection<IPersistentBeanPropertyDescriptor> propertyDescriptors = null;
	private Collection<IPersistentBeanPropertyDescriptor> allPropertyDescriptors = null;
	private Set<String> abandonedProperties = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor#getTableName()
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
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor#getPersistentProperties()
	 */
	@Override
	public Collection<IPersistentBeanPropertyDescriptor> getPersistentProperties() {
		if (this.allPropertyDescriptors == null) {
			synchronized (this) {
				if (this.allPropertyDescriptors == null) {
					List<IPersistentBeanPropertyDescriptor> list = new ArrayList<IPersistentBeanPropertyDescriptor>();

					Collection<IPropertyDescriptor> descriptors = this.getProperties();
					for (IPropertyDescriptor descriptor : descriptors) {
						if (descriptor instanceof IPersistentBeanPropertyDescriptor) {
							if (!this.isAbandoned(descriptor.getName())) {
								// skip the abandoned property
								list.add((IPersistentBeanPropertyDescriptor) descriptor);
							}
						}
					}
					this.allPropertyDescriptors = list;
				}
			}
		}
		return this.allPropertyDescriptors;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor#getDeclaredPersistentProperties()
	 */
	@Override
	public Collection<IPersistentBeanPropertyDescriptor> getDeclaredPersistentProperties() {
		if (this.propertyDescriptors == null) {
			synchronized (this) {
				if (this.propertyDescriptors == null) {
					List<IPersistentBeanPropertyDescriptor> list = new ArrayList<IPersistentBeanPropertyDescriptor>();

					Collection<IPropertyDescriptor> descriptors = this.getDeclaredProperties();
					for (IPropertyDescriptor descriptor : descriptors) {
						if (descriptor instanceof IPersistentBeanPropertyDescriptor) {
							list.add((IPersistentBeanPropertyDescriptor) descriptor);
						}
					}
					this.propertyDescriptors = list;
				}
			}
		}
		return this.propertyDescriptors;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor#getAbandonedProperties()
	 */
	@Override
	public Set<String> getAbandonedProperties() {
		return this.abandonedProperties;
	}

	/**
	 * @param abandonedProperties
	 *            the abandonedProperties to set
	 */
	public void setAbandonedProperties(Set<String> abandonedProperties) {
		this.abandonedProperties = abandonedProperties;
	}

	/**
	 * check the given property is abandoned or not
	 * 
	 * @param propertyName
	 * @return
	 */
	public boolean isAbandoned(String propertyName) {
		return this.getAbandonedProperties() == null ? false : this.getAbandonedProperties().contains(propertyName);
	}
}
