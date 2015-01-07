/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * length of string. if object is not a string, use
 * {@linkplain Object#toString()} to get string value.<br>
 * default value of {@linkplain #min} is 0, {@linkplain #max} is
 * {@linkplain Integer#MAX_VALUE}.
 * 
 * @author brad.wu
 */
public class Length extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 2516697380188994612L;

	private int min = 0;
	private int max = Integer.MAX_VALUE;

	public Length(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

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
