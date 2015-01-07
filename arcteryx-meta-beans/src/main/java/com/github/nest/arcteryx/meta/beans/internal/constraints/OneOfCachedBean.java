/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * one of cached bean constraint
 * 
 * @author brad.wu
 */
public class OneOfCachedBean extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 3572721751082867326L;

	private Class<?> cachedBeanClass = null;

	public OneOfCachedBean(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

	/**
	 * @return the cachedBeanClass
	 */
	protected Class<?> getCachedBeanClass() {
		return cachedBeanClass;
	}

	/**
	 * @param cachedBeanClass
	 *            the cachedBeanClass to set
	 */
	protected void setCachedBeanClass(Class<?> cachedBeanClass) {
		this.cachedBeanClass = cachedBeanClass;
	}
}
