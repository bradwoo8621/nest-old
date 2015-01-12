/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanCreator;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanDestroyer;
import com.github.nest.arcteryx.meta.beans.IBeanFinder;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanValidator;
import com.github.nest.arcteryx.meta.internal.ResourceDescriptor;

/**
 * Bean descriptor
 * 
 * @author brad.wu
 */
public class BeanDescriptor extends ResourceDescriptor implements IBeanDescriptor {
	private static final long serialVersionUID = -292539093442024519L;

	private IBeanConstraint constraint = null;
	private Collection<IBeanPropertyDescriptor> beanDescriptors = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getBeanDescriptorContext()
	 */
	@Override
	public IBeanDescriptorContext getBeanDescriptorContext() {
		return this.getContext();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getBeanClass()
	 */
	@Override
	public Class<?> getBeanClass() {
		return this.getResourceClass();
	}

	/**
	 * @param beanClass
	 *            the beanClass to set
	 */
	public void setBeanClass(Class<?> beanClass) {
		this.setResourceClass(beanClass);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getConstraint()
	 */
	@Override
	public IBeanConstraint getConstraint() {
		return this.constraint;
	}

	/**
	 * @param constraint
	 *            the constraint to set
	 */
	public void setConstraint(IBeanConstraint constraint) {
		this.constraint = constraint;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getValidator()
	 */
	@Override
	public IBeanValidator getValidator() {
		return this.getOperator(IBeanValidator.CODE);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getCreator()
	 */
	@Override
	public IBeanCreator getCreator() {
		return this.getOperator(IBeanCreator.CODE);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getDestoryer()
	 */
	@Override
	public IBeanDestroyer getDestoryer() {
		return this.getOperator(IBeanDestroyer.CODE);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getFinder()
	 */
	@Override
	public IBeanFinder getFinder() {
		return this.getOperator(IBeanFinder.CODE);
	}

	/**
	 * Will cache the {@linkplain IBeanPropertyDescriptor} at first call, and
	 * never changed again.
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getBeanProperties()
	 */
	@Override
	public Collection<IBeanPropertyDescriptor> getBeanProperties() {
		if (this.beanDescriptors == null) {
			synchronized (this) {
				if (this.beanDescriptors == null) {
					List<IBeanPropertyDescriptor> beanDescriptors = new ArrayList<IBeanPropertyDescriptor>();

					Collection<IPropertyDescriptor> descriptors = this.getDeclaredProperties();
					for (IPropertyDescriptor descriptor : descriptors) {
						if (descriptor instanceof IBeanPropertyDescriptor) {
							beanDescriptors.add((IBeanPropertyDescriptor) descriptor);
						}
					}
					this.beanDescriptors = beanDescriptors;
				}
			}
		}
		return this.beanDescriptors;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanDescriptor#getDeclaredBeanProperties()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<IBeanPropertyDescriptor> getDeclaredBeanProperties() {
		return (Collection<IBeanPropertyDescriptor>) (this.beanDescriptors == null ? Collections.emptyList()
				: this.beanDescriptors);
	}
}
