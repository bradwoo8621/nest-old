/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.validation.hibernate.validators.ogn.ObjectGraphNavigationResult;
import com.github.nest.arcteryx.validation.hibernate.validators.ogn.ObjectGraphNavigatorRegistry;

/**
 * abstract constraint validator, which consider the target property from
 * definition
 * 
 * @author brad.wu
 */
public abstract class AbstractConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {
	private String target = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(A parameters) {
		this.target = getTarget(parameters);
	}

	/**
	 * get target from annotation
	 * 
	 * @param parameters
	 * @return
	 */
	protected abstract String getTarget(A parameters);

	/**
	 * @return the target
	 */
	protected String getTarget() {
		return target.trim();
	}

	/**
	 * is target appointed
	 * 
	 * @return
	 */
	protected boolean isTargetAppointed() {
		return !StringUtils.isBlank(getTarget());
	}

	/**
	 * get target value
	 * 
	 * @param value
	 * @return
	 */
	protected ObjectGraphNavigationResult getTargetValue(Object value) {
		if (value == null) {
			return null;
		}

		Object valueToValidate = value;
		String target = getTarget();
		if (target.length() > 0) {
			final String[] chunks = target.split(":", 2);
			final String ognId, path;
			if (chunks.length == 1) {
				ognId = "";
				path = chunks[0];
			} else {
				ognId = chunks[0];
				path = chunks[1];
			}
			return ObjectGraphNavigatorRegistry.getObjectGraphNavigator(ognId).navigateTo(valueToValidate, path);
		}

		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(T value, ConstraintValidatorContext context) {
		if (this.isTargetAppointed()) {
			return checkValue(getTargetValue(value), value, context);
		} else {
			return isValueValid(value, context);
		}
	}

	/**
	 * check the target value is valid or not. default is use the target value
	 * to call {@linkplain #isValueValid(Object, ConstraintValidatorContext)}
	 * 
	 * @param targetValue
	 * @param root
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean checkValue(ObjectGraphNavigationResult targetValue, T root, ConstraintValidatorContext context) {
		return isValueValid((T) targetValue.getTarget(), context);
	}

	/**
	 * check value is valid or not
	 * 
	 * @param value
	 * @param context
	 * @return
	 */
	protected abstract boolean isValueValid(T value, ConstraintValidatorContext context);
}
