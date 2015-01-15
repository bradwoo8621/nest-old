/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanConstraint;

/**
 * bean constraints, which can contain more than one constraint.
 * 
 * @author brad.wu
 */
public class BeanConstraints extends AbstractBeanConstraint {
	private static final long serialVersionUID = -7579419987708092073L;

	private List<IBeanConstraint> constraints = null;

	/**
	 * @return the constraints
	 */
	public List<IBeanConstraint> getConstraints() {
		return constraints;
	}

	/**
	 * @param constraints
	 *            the constraints to set
	 */
	public void setConstraints(List<IBeanConstraint> constraints) {
		this.constraints = constraints;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.constraints.AbstractBeanPropertyConstraint#getConstraintsRecursive()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IBeanConstraint> getConstraintsRecursive() {
		List<IBeanConstraint> list = new LinkedList<IBeanConstraint>();
		for (IBeanConstraint constraint : this.getConstraints()) {
			list.addAll(constraint.getConstraintsRecursive());
		}
		return list;
	}
}
