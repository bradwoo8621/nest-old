/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

import java.util.List;

import com.github.nest.arcteryx.meta.IResourceDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.constraints.PropertyConstraints;
import com.github.nest.arcteryx.meta.internal.PropertyDescriptor;

/**
 * Bean property descriptor
 * 
 * @author brad.wu
 */
public class BeanPropertyDescriptor extends PropertyDescriptor implements IBeanPropertyDescriptor {
	private static final long serialVersionUID = -8690855571159823110L;

	private Object defaultValue = null;
	private IBeanPropertyConstraint constraint = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor#getDefaultValue()
	 */
	@Override
	public Object getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor#getConstraint()
	 */
	@Override
	public IBeanPropertyConstraint getConstraint() {
		return this.constraint;
	}

	/**
	 * @param constraint
	 *            the constraint to set
	 */
	public void setConstraint(IBeanPropertyConstraint constraint) {
		this.constraint = constraint;
	}

	/**
	 * set constraints.
	 * 
	 * @param constraints
	 */
	public void setConstraints(List<IBeanPropertyConstraint> constraints) {
		assert constraints != null : "Constraints cannot be null.";

		PropertyConstraints all = new PropertyConstraints();
		all.setConstraints(constraints);
		setConstraint(all);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor#getBeanDescriptor()
	 */
	@Override
	public IBeanDescriptor getBeanDescriptor() {
		return this.getResourceDescriptor(IBeanDescriptor.class);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.PropertyDescriptor#setResourceDescriptor(com.github.nest.arcteryx.meta.IResourceDescriptor)
	 */
	@Override
	public void setResourceDescriptor(IResourceDescriptor resourceDescriptor) {
		assert resourceDescriptor instanceof IBeanDescriptor : "Resource descriptor must be an instance of "
				+ IBeanDescriptor.class;
		super.setResourceDescriptor(resourceDescriptor);
	}
}
