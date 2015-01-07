/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.math.BigDecimal;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * constraint of number.<br>
 * default value of {@linkplain #excludeMin} and {@linkplain #excludeMax} are
 * false.
 * 
 * @author brad.wu
 */
public class Number extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 3305823600464488631L;

	private BigDecimal min = null;
	private boolean excludeMin = false;
	private BigDecimal max = null;
	private boolean excludeMax = false;

	public Number(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

	/**
	 * @return the min
	 */
	protected BigDecimal getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	protected void setMin(BigDecimal min) {
		this.min = min;
	}

	/**
	 * @return the excludeMin
	 */
	protected boolean isExcludeMin() {
		return excludeMin;
	}

	/**
	 * @param excludeMin
	 *            the excludeMin to set
	 */
	protected void setExcludeMin(boolean excludeMin) {
		this.excludeMin = excludeMin;
	}

	/**
	 * @return the max
	 */
	protected BigDecimal getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	protected void setMax(BigDecimal max) {
		this.max = max;
	}

	/**
	 * @return the excludeMax
	 */
	protected boolean isExcludeMax() {
		return excludeMax;
	}

	/**
	 * @param excludeMax
	 *            the excludeMax to set
	 */
	protected void setExcludeMax(boolean excludeMax) {
		this.excludeMax = excludeMax;
	}
}
