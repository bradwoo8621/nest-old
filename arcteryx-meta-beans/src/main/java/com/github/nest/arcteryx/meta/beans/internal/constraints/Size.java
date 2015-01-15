/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.Arrays;

import com.github.nest.arcteryx.meta.beans.ConstraintApplyTo;

/**
 * size of collection or array.<br>
 * default value of {@linkplain #min} is 0, {@linkplain #max} is
 * {@linkplain Integer#MAX_VALUE}.
 * 
 * @author brad.wu
 */
public class Size extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -4613091346748856910L;

	private int min = 0;
	private int max = Integer.MAX_VALUE;

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.constraints.AbstractBeanPropertyConstraint#getAppliesTo()
	 */
	@Override
	public ConstraintApplyTo getAppliesTo() {
		return ConstraintApplyTo.CONTAINER;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalToString() + " [min=" + min + ", max=" + max + ", getMessageTemplate()="
				+ getMessageTemplate() + ", getWhen()=" + getWhen() + ", getTarget()=" + getTarget()
				+ ", getErrorCode()=" + getErrorCode() + ", getProfiles()=" + Arrays.toString(getProfiles())
				+ ", getSeverity()=" + getSeverity() + ", getConstraintsRecursive()=" + getConstraintsRecursive() + "]";
	}
}
