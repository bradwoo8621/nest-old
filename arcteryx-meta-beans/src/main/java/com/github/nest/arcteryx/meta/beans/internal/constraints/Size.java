/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

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
	protected int getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	protected void setMin(int min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	protected int getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	protected void setMax(int max) {
		this.max = max;
	}
}
