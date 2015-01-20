/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;

/**
 * date range validator
 * 
 * @author brad.wu
 */
public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String format;
	private String max;
	private boolean excludeMax = false;
	private String min;
	private boolean excludeMin = false;
	private int tolerance;

	private transient Long maxMillis;
	private transient Long minMillis;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(DateRange constraintAnnotation) {
		setMin(constraintAnnotation.min());
		setMax(constraintAnnotation.max());
		setFormat(constraintAnnotation.format());
		setTolerance(constraintAnnotation.tolerance());
		setExcludeMax(constraintAnnotation.excludeMax());
		setExcludeMin(constraintAnnotation.excludeMin());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object valueToValidate, ConstraintValidatorContext context) {
		if (valueToValidate == null)
			return true;

		long valueInMillis = -1;

		// check if the value is a Date
		if (valueToValidate instanceof Date)
			valueInMillis = ((Date) valueToValidate).getTime();
		else if (valueToValidate instanceof Calendar)
			valueInMillis = ((Calendar) valueToValidate).getTime().getTime();
		else {
			// see if we can extract a date based on the object's String
			// representation
			final String stringValue = valueToValidate.toString();
			try {
				if (format != null)
					try {
						valueInMillis = new SimpleDateFormat(format).parse(stringValue).getTime();
					} catch (final ParseException ex) {
						logger.debug("valueToValidate not parsable with specified format {1}", format, ex);
					}

				if (valueInMillis == -1)
					valueInMillis = DateFormat.getDateTimeInstance().parse(stringValue).getTime();
			} catch (final ParseException ex) {
				logger.debug("valueToValidate is unparsable.", ex);
				return false;
			}
		}

		boolean legal = true;
		if (this.isExcludeMax()) {
			legal = valueInMillis < getMaxMillis();
		} else {
			legal = valueInMillis <= getMaxMillis();
		}

		if (legal) {
			if (this.isExcludeMin()) {
				legal = valueInMillis > getMinMillis();
			} else {
				legal = valueInMillis >= getMinMillis();
			}
		}
		return legal;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(String max) {
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
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(String min) {
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
	 * @return the tolerance
	 */
	public int getTolerance() {
		return tolerance;
	}

	/**
	 * @param tolerance
	 *            the tolerance to set
	 */
	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	private long getMaxMillis() throws BeanValidationException {
		if (maxMillis == null) {
			if (max == null || max.length() == 0)
				return Long.MAX_VALUE;

			if ("now".equals(max))
				return System.currentTimeMillis() + tolerance;

			if ("today".equals(max)) {
				final Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.add(Calendar.DAY_OF_YEAR, 1);
				cal.add(Calendar.MILLISECOND, -1);
				return cal.getTimeInMillis() + tolerance;
			}

			if ("tomorrow".equals(max)) {
				final Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.add(Calendar.DAY_OF_YEAR, 2);
				cal.add(Calendar.MILLISECOND, -1);
				return cal.getTimeInMillis() + tolerance;
			}

			if ("yesterday".equals(max)) {
				final Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.add(Calendar.MILLISECOND, -1);
				return cal.getTimeInMillis() + tolerance;
			}

			if (format != null && format.length() > 0) {
				final SimpleDateFormat sdf = new SimpleDateFormat(format);
				try {
					maxMillis = sdf.parse(max).getTime() + tolerance;
				} catch (final ParseException e) {
					throw new BeanValidationException("Unable to parse the max Date String", e);
				}
			} else
				try {
					maxMillis = DateFormat.getDateTimeInstance().parse(max).getTime() + tolerance;
				} catch (final ParseException e) {
					throw new BeanValidationException("Unable to parse the max Date String", e);
				}
		}
		return maxMillis;
	}

	private long getMinMillis() throws BeanValidationException {
		if (minMillis == null) {
			if (min == null || min.length() == 0)
				return 0L;

			if ("now".equals(min))
				return System.currentTimeMillis() - tolerance;

			if ("today".equals(min)) {
				final Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				return cal.getTimeInMillis() - tolerance;
			}

			if ("tomorrow".equals(min)) {
				final Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.add(Calendar.DAY_OF_YEAR, 1);
				return cal.getTimeInMillis() - tolerance;
			}

			if ("yesterday".equals(min)) {
				final Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.add(Calendar.DAY_OF_YEAR, -1);
				return cal.getTimeInMillis() - tolerance;
			}

			if (format != null && format.length() > 0) {
				final SimpleDateFormat sdf = new SimpleDateFormat(format);
				try {
					minMillis = sdf.parse(min).getTime() - tolerance;
				} catch (final ParseException e) {
					throw new BeanValidationException("Unable to parse the min Date String", e);
				}
			} else
				try {
					minMillis = DateFormat.getDateTimeInstance().parse(min).getTime() - tolerance;
				} catch (final ParseException e) {
					throw new BeanValidationException("Unable to parse the min Date String", e);
				}
		}
		return minMillis;
	}
}
