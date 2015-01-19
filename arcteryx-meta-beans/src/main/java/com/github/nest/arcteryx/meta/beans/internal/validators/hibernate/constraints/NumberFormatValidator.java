/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * number format validator
 * 
 * @author brad.wu
 */
public class NumberFormatValidator implements ConstraintValidator<NumberFormat, Object> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private int minIntegerDigits = 0;
	private int minFractionDigits = 0;
	private int maxIntegerDigits = Integer.MAX_VALUE;
	private int maxFractionDigits = Integer.MAX_VALUE;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NumberFormat constraintAnnotation) {
		this.setMinIntegerDigits(constraintAnnotation.minIntegerDigits());
		this.setMaxIntegerDigits(constraintAnnotation.maxIntegerDigits());
		this.setMinFractionDigits(constraintAnnotation.minFractionDigits());
		this.setMaxFractionDigits(constraintAnnotation.maxFractionDigits());
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

		final int fractLen, intLen;
		if (value instanceof Integer) {
			final int intValue = (Integer) value;
			intLen = intValue == 0 ? 1 : (int) Math.log10(intValue) + 1;
			fractLen = 0;
		} else if (value instanceof Long) {
			final long longValue = (Long) value;
			intLen = longValue == 0 ? 1 : (int) Math.log10(longValue) + 1;
			fractLen = 0;
		} else if (value instanceof Short) {
			final short shortValue = (Short) value;
			intLen = shortValue == 0 ? 1 : (int) Math.log10(shortValue) + 1;
			fractLen = 0;
		} else if (value instanceof Byte) {
			final byte byteValue = (Byte) value;
			intLen = byteValue == 0 ? 1 : (int) Math.log10(byteValue) + 1;
			fractLen = 0;
		} else if (value instanceof BigInteger) {
			final long bigIntegerValue = ((BigInteger) value).longValue();
			intLen = bigIntegerValue == 0 ? 1 : (int) Math.log10(bigIntegerValue) + 1;
			fractLen = 0;
		} else {
			BigDecimal bigDecimalvalue = null;
			if (value instanceof BigDecimal)
				bigDecimalvalue = (BigDecimal) value;
			else
				try {
					bigDecimalvalue = new BigDecimal(value.toString());
				} catch (final NumberFormatException ex) {
					logger.debug("Failed to parse numeric value: " + value, ex);
					return false;
				}
			final int valueScale = bigDecimalvalue.scale();
			final long longValue = bigDecimalvalue.longValue();
			intLen = longValue == 0 ? 1 : (int) Math.log10(longValue) + 1;
			fractLen = valueScale > 0 ? valueScale : 0;
		}

		return intLen <= this.getMaxIntegerDigits() && intLen >= this.getMinIntegerDigits()
				&& fractLen <= this.getMaxFractionDigits() && fractLen >= this.getMinFractionDigits();
	}

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
}
