/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;

/**
 * property constraints, which can contain more than one constraint. constraints
 * which are contained must have same property descriptor.
 * 
 * @author brad.wu
 */
public class PropertyConstraints extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -7579419987708092073L;

	private List<IBeanPropertyConstraint> constraints = null;

	/**
	 * @return the constraints
	 */
	public List<IBeanPropertyConstraint> getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	public void setConstraints(List<IBeanPropertyConstraint> constraints) {
		this.constraints = constraints;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.constraints.AbstractBeanPropertyConstraint#getConstraintsRecursive()
	 */
	@Override
	public List<IBeanPropertyConstraint> getConstraintsRecursive() {
		List<IBeanPropertyConstraint> list = new LinkedList<IBeanPropertyConstraint>();
		for (IBeanPropertyConstraint constraint : this.getConstraints()) {
			list.addAll(constraint.getConstraintsRecursive());
		}
		return list;
	}
}
