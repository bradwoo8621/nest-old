/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators;

import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import com.github.nest.arcteryx.validation.hibernate.constraints.Length;

/**
 * Check that the character sequence length is between min and max.
 * 
 * @author brad.wu
 */
public class LengthValidator extends AbstractConstraintValidator<Length, Object> {
	private static final Log log = LoggerFactory.make();

	private int min;
	private int max;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.AbstractConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Length parameters) {
		super.initialize(parameters);
		min = parameters.min();
		max = parameters.max();
		validateParameters();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.AbstractConstraintValidator#getTarget(java.lang.annotation.Annotation)
	 */
	@Override
	protected String getTarget(Length parameters) {
		return parameters.target();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.AbstractConstraintValidator#getWhen(java.lang.annotation.Annotation)
	 */
	@Override
	protected String getWhen(Length parameters) {
		return parameters.when();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.AbstractConstraintValidator#isValueValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	protected boolean isValueValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		int length = value.toString().length();
		return length >= min && length <= max;
	}

	/**
	 * validate parameters
	 */
	private void validateParameters() {
		if (min < 0) {
			throw log.getMinCannotBeNegativeException();
		}
		if (max < 0) {
			throw log.getMaxCannotBeNegativeException();
		}
		if (max < min) {
			throw log.getLengthCannotBeNegativeException();
		}
	}
}
