/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * not negative validator
 * 
 * @author brad.wu
 */
public class NotNegativeValidator implements ConstraintValidator<NotNegative, Object> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NotNegative constraintAnnotation) {
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

		if (value instanceof Number) {
			if (value instanceof Float || value instanceof Double)
				return ((Number) value).doubleValue() >= 0;
			if (value instanceof BigDecimal)
				return ((BigDecimal) value).compareTo(BigDecimal.ZERO) >= 0;
			return ((Number) value).longValue() >= 0;
		}

		final String stringValue = value.toString();
		try {
			return Double.parseDouble(stringValue) >= 0;
		} catch (final NumberFormatException e) {
			return false;
		}
	}
}
