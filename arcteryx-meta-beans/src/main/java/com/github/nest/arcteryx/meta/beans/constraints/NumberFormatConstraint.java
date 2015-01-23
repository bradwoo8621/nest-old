/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.constraints;

import java.util.Arrays;

import com.github.nest.arcteryx.meta.beans.annotation.NumberFormat;

/**
 * number format constraint. default value of {@linkplain #minIntegerDigits} and
 * {@linkplain #minFractionDigits} is 0.
 * 
 * @author brad.wu
 */
public class NumberFormatConstraint extends AbstractBeanPropertyConstraint<NumberFormat> {
	private static final long serialVersionUID = 2409550204243550060L;

	private int minIntegerDigits = 0;
	private int minFractionDigits = 0;
	private int maxIntegerDigits = Integer.MAX_VALUE;
	private int maxFractionDigits = Integer.MAX_VALUE;

	/**
	 * @return the minIntegerDigits
	 */
	public int getMinIntegerDigits() {
		return minIntegerDigits;
	}

	/**
	 * @param minIntegerDigits
	 *            the minIntegerDigits to set
	 */
	public void setMinIntegerDigits(int minIntegerDigits) {
		this.minIntegerDigits = minIntegerDigits;
	}

	/**
	 * @return the minFractionDigits
	 */
	public int getMinFractionDigits() {
		return minFractionDigits;
	}

	/**
	 * @param minFractionDigits
	 *            the minFractionDigits to set
	 */
	public void setMinFractionDigits(int minFractionDigits) {
		this.minFractionDigits = minFractionDigits;
	}

	/**
	 * @return the maxIntegerDigits
	 */
	public int getMaxIntegerDigits() {
		return maxIntegerDigits;
	}

	/**
	 * @param maxIntegerDigits
	 *            the maxIntegerDigits to set
	 */
	public void setMaxIntegerDigits(int maxIntegerDigits) {
		this.maxIntegerDigits = maxIntegerDigits;
	}

	/**
	 * @return the maxFractionDigits
	 */
	public int getMaxFractionDigits() {
		return maxFractionDigits;
	}

	/**
	 * @param maxFractionDigits
	 *            the maxFractionDigits to set
	 */
	public void setMaxFractionDigits(int maxFractionDigits) {
		this.maxFractionDigits = maxFractionDigits;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.constraints.AbstractBeanPropertyConstraint#configure(java.lang.annotation.Annotation)
	 */
	@Override
	public void configure(NumberFormat annotation) {
		super.configure(annotation);
		this.setMinIntegerDigits(annotation.minIntegerDigits());
		this.setMaxIntegerDigits(annotation.maxIntegerDigits());
		this.setMinFractionDigits(annotation.minFractionDigits());
		this.setMaxFractionDigits(annotation.maxFractionDigits());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumberFormat [minIntegerDigits=" + minIntegerDigits + ", minFractionDigits=" + minFractionDigits
				+ ", maxIntegerDigits=" + maxIntegerDigits + ", maxFractionDigits=" + maxFractionDigits
				+ ", getMessageTemplate()=" + getMessageTemplate() + ", getWhen()=" + getWhen() + ", getTarget()="
				+ getTarget() + ", getErrorCode()=" + getErrorCode() + ", getProfiles()="
				+ Arrays.toString(getProfiles()) + ", getSeverity()=" + getSeverity() + ", getAppliesTo()="
				+ getAppliesTo() + "]";
	}
}
