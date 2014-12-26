/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ValidationException;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageBeanShellImpl extends AbstractExpressionLanguage {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating BeanShell expression: {1}", expression);
		try {
			final Interpreter interpreter = new Interpreter();
			interpreter.eval("setAccessibility(true)"); // turn off access
														// restrictions
			for (final Entry<String, ?> entry : values.entrySet())
				interpreter.set(entry.getKey(), entry.getValue());
			return interpreter.eval(expression);
		} catch (final EvalError ex) {
			throw new ValidationException("Evaluating BeanShell expression failed: " + expression, ex);
		}
	}
}