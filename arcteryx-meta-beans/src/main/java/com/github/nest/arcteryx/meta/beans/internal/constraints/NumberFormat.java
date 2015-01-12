/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;


/**
 * number format constraint. default value of {@linkplain #integerDigits} and
 * {@linkplain #fractionDigits} is {@linkplain Integer#MAX_VALUE}.
 * 
 * @author brad.wu
 */
public class NumberFormat extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 2409550204243550060L;

	private int integerDigits = Integer.MAX_VALUE;
	private int fractionDigits = Integer.MAX_VALUE;

	/**
	 * @return the integerDigits
	 */
	protected int getIntegerDigits() {
		return integerDigits;
	}

	/**
	 * @param integerDigits
	 *            the integerDigits to set
	 */
	protected void setIntegerDigits(int integerDigits) {
		this.integerDigits = integerDigits;
	}

	/**
	 * @return the fractionDigits
	 */
	protected int getFractionDigits() {
		return fractionDigits;
	}

	/**
	 * @param fractionDigits
	 *            the fractionDigits to set
	 */
	protected void setFractionDigits(int fractionDigits) {
		this.fractionDigits = fractionDigits;
	}
}
