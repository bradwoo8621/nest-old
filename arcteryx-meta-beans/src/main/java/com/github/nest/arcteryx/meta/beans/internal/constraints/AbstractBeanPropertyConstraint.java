/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.ConstraintApplyTo;
import com.github.nest.arcteryx.meta.beans.ConstraintLevel;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;

/**
 * bean property constraint
 * 
 * @author brad.wu
 */
public abstract class AbstractBeanPropertyConstraint extends AbstractConstraint implements IBeanPropertyConstraint {
	private static final long serialVersionUID = 7692841064131390892L;

	private String target = null;
	private ConstraintApplyTo appliesTo = ConstraintApplyTo.defaultApplyTo();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getTarget()
	 */
	@Override
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getAppliesTo()
	 */
	@Override
	public ConstraintApplyTo getAppliesTo() {
		return this.appliesTo;
	}

	/**
	 * @param appliesTo
	 *            the appliesTo to set
	 */
	public void setAppliesTo(ConstraintApplyTo appliesTo) {
		this.appliesTo = appliesTo;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getConstraintsRecursive()
	 */
	@Override
	public List<IBeanPropertyConstraint> getConstraintsRecursive() {
		List<IBeanPropertyConstraint> list = new LinkedList<IBeanPropertyConstraint>();
		list.add(this);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#getLevel()
	 */
	@Override
	public ConstraintLevel getLevel() {
		return ConstraintLevel.PROPERTY;
	}
}
