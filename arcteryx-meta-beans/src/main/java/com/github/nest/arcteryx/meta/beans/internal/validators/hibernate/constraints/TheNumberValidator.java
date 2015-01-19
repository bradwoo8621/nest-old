/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * number validator
 * 
 * @author brad.wu
 */
public class TheNumberValidator implements ConstraintValidator<TheNumber, Object> {
	private double min = Double.NEGATIVE_INFINITY;
	private boolean excludeMin = false;
	private double max = Double.POSITIVE_INFINITY;
	private boolean excludeMax = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(TheNumber constraintAnnotation) {
		this.setMin(constraintAnnotation.min());
		this.setExcludeMin(constraintAnnotation.excludeMin());
		this.setMax(constraintAnnotation.max());
		this.setExcludeMax(constraintAnnotation.excludeMax());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null)
			return true;

		double doubleValue = 0;
		if (value instanceof java.lang.Number) {
			doubleValue = ((java.lang.Number) value).doubleValue();
		} else {
			try {
				doubleValue = Double.parseDouble(value.toString());
			} catch (NumberFormatException e) {
				return false;
			}
		}
		// validate
		boolean pass = true;
		if (this.getMin() != Double.NEGATIVE_INFINITY) {
			if (this.isExcludeMin()) {
				pass = doubleValue > this.getMin();
			} else {
				pass = doubleValue >= this.getMin();
			}
		}
		if (pass) {
			if (this.getMax() != Double.POSITIVE_INFINITY) {
				if (this.isExcludeMax()) {
					pass = doubleValue < this.getMax();
				} else {
					pass = doubleValue <= this.getMax();
				}
			}
		}
		return pass;
	}

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
}
