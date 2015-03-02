/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import org.apache.commons.lang3.ArrayUtils;

import com.github.nest.arcteryx.persistent.CascadeType;
import com.github.nest.arcteryx.persistent.IOneToOnePersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;

/**
 * one-to-one persistent column
 * 
 * @author brad.wu
 */
public class OneToOnePersistentColumn extends AbstractPersistentColumn implements IOneToOnePersistentColumn {
	private static final long serialVersionUID = 5375362109834504369L;

	private Class<?> beanClass = null;
	private CascadeType[] cascadeTypes = DEFAULT_CASCADE_TYPES;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IOneToOnePersistentColumn#getSubordinateBean()
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
	 * @see com.github.nest.arcteryx.persistent.IOneToOnePersistentColumn#getCascadeTypes()
	 */
	@Override
	public CascadeType[] getCascadeTypes() {
		return ArrayUtils.isEmpty(this.cascadeTypes) ? DEFAULT_CASCADE_TYPES : this.cascadeTypes;
	}

	/**
	 * set as null means reset to default.
	 * 
	 * @param cascadeTypes
	 *            the cascadeTypes to set
	 */
	public void setCascadeTypes(CascadeType[] cascadeTypes) {
		this.cascadeTypes = cascadeTypes;
	}
}
