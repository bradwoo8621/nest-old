/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * abstract expression language
 * 
 * @author brad.wu
 */
public abstract class AbstractExpressionLanguage implements ExpressionLanguage {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluateAsBoolean(java.lang.String,
	 *      java.util.Map)
	 */
	public boolean evaluateAsBoolean(final String expression, final Map<String, ?> values) {
		final Object result = evaluate(expression, values);
		if (!(result instanceof Boolean))
			throw new ValidationException("The script must return a boolean value.");
		return (Boolean) result;
	}
}
