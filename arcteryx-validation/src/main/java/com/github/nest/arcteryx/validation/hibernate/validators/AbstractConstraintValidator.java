/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage;
import com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguageRegistry;
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
	private boolean whenDefined = false;
	private String whenExpr = null;
	private String whenLang = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(A parameters) {
		this.target = getTarget(parameters);
		String when = getWhen(parameters);
		if (!StringUtils.isBlank(when)) {
			this.whenDefined = true;
			final String[] parts = when.split(":", 2);
			if (parts.length == 0)
				throw new ValidationException("[when] is missing the scripting language declaration");
			this.whenLang = parts[0];
			this.whenExpr = parts[1];
		}
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
	 * check <code>target</code> is given or not
	 * 
	 * @return
	 */
	protected boolean isTargetDefined() {
		return !StringUtils.isBlank(getTarget());
	}

	/**
	 * get when from annotation
	 * 
	 * @param parameters
	 * @return
	 */
	protected abstract String getWhen(A parameters);

	/**
	 * get when expression
	 * 
	 * @return
	 */
	protected String getWhenExpression() {
		return this.whenExpr.trim();
	}

	/**
	 * get when language
	 * 
	 * @return
	 */
	protected String getWhenLanguage() {
		return this.whenLang.trim();
	}

	/**
	 * check <code>when</code> is given or not
	 * 
	 * @return
	 */
	protected boolean isWhenDefined() {
		return this.whenDefined;
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
		// check when
		// TODO, how to get current validateObject?
		if (!isActive(value, null)) {
			return true;
		}

		if (this.isTargetDefined()) {
			return checkValue(getTargetValue(value), value, context);
		} else {
			return isValueValid(value, context);
		}
	}

	/**
	 * check <code>when</code> condition, if not passed, return false.
	 * 
	 * @return
	 */
	protected boolean isActive(T valueToValidate, Object validatedObject) {
		if (!this.isWhenDefined()) {
			return true;
		}

		Map<String, Object> values = new LinkedHashMap<String, Object>();
		values.put("_value", valueToValidate);
		values.put("_this", validatedObject);

		final ExpressionLanguage el = ExpressionLanguageRegistry.getExpressionLanguage(this.getWhenLanguage());
		return el.evaluateAsBoolean(this.getWhenExpression(), values);
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
