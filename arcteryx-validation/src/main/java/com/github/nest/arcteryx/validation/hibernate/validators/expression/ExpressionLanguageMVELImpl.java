/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;

import javax.validation.ValidationException;

import org.mvel2.MVEL;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ObjectCache;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageMVELImpl extends AbstractExpressionLanguage {
	private final ObjectCache<String, Object> expressionCache = new ObjectCache<String, Object>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating MVEL expression: {1}", expression);
		try {
			Object expr = expressionCache.get(expression);
			if (expr == null) {
				expr = MVEL.compileExpression(expression);
				expressionCache.put(expression, expr);
			}
			return MVEL.executeExpression(expr, values);
		} catch (final Exception ex) {
			throw new ValidationException("Evaluating MVEL expression failed: " + expression, ex);
		}
	}
}