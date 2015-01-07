/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * property constraints, which can contain more than one constraint. constraints
 * which are contained must have same property descriptor.
 * 
 * @author brad.wu
 */
public class PropertyConstraints extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -7579419987708092073L;

	private List<IBeanPropertyConstraint> constraints = null;

	public PropertyConstraints(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

	/**
	 * @return the constraints
	 */
	protected List<IBeanPropertyConstraint> getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	protected void setConstraints(List<IBeanPropertyConstraint> constraints) {
		this.constraints = constraints;
	}
}
