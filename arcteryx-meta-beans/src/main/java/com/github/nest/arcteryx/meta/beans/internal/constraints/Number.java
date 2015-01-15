/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.Arrays;

/**
 * constraint of number.<br>
 * default value of {@linkplain #excludeMin} and {@linkplain #excludeMax} are
 * false.
 * 
 * @author brad.wu
 */
public class Number extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 3305823600464488631L;

	private double min = Double.NEGATIVE_INFINITY;
	private boolean excludeMin = false;
	private double max = Double.POSITIVE_INFINITY;
	private boolean excludeMax = false;

	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * @return the excludeMin
	 */
	public boolean isExcludeMin() {
		return excludeMin;
	}

	/**
	 * @param excludeMin
	 *            the excludeMin to set
	 */
	public void setExcludeMin(boolean excludeMin) {
		this.excludeMin = excludeMin;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}

	/**
	 * @return the excludeMax
	 */
	public boolean isExcludeMax() {
		return excludeMax;
	}

	/**
	 * @param excludeMax
	 *            the excludeMax to set
	 */
	public void setExcludeMax(boolean excludeMax) {
		this.excludeMax = excludeMax;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalToString() + " [min=" + min + ", excludeMin=" + excludeMin + ", max=" + max
				+ ", excludeMax=" + excludeMax + ", getMessageTemplate()=" + getMessageTemplate() + ", getWhen()="
				+ getWhen() + ", getTarget()=" + getTarget() + ", getErrorCode()=" + getErrorCode()
				+ ", getProfiles()=" + Arrays.toString(getProfiles()) + ", getSeverity()=" + getSeverity()
				+ ", getAppliesTo()=" + getAppliesTo() + "]";
	}
}
