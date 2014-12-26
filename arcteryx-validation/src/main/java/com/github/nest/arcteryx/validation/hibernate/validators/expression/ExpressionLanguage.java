/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public interface ExpressionLanguage {
	/**
	 * Evaluates the given expression.
	 * 
	 * @param expression
	 *            the expression to evaluate
	 * @param values
	 *            context values passed to the interpreter
	 * @return the result of the expression evaluation
	 */
	Object evaluate(String expression, Map<String, ?> values);

	/**
	 * Evaluates the given expression and expects it to return a boolean.
	 * 
	 * @param expression
	 *            the expression to evaluate
	 * @param values
	 *            context values passed to the interpreter
	 * @return the result of the expression evaluation
	 */
	boolean evaluateAsBoolean(String expression, Map<String, ?> values);
}
