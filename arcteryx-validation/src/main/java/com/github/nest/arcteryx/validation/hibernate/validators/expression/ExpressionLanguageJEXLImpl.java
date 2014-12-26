/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;

import javax.validation.ValidationException;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ObjectCache;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageJEXLImpl extends AbstractExpressionLanguage {
	private static final JexlEngine jexl = new JexlEngine();

	private final ObjectCache<String, Expression> expressionCache = new ObjectCache<String, Expression>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating JEXL expression: {1}", expression);
		try {
			Expression expr = expressionCache.get(expression);
			if (expr == null) {
				expr = jexl.createExpression(expression);
				expressionCache.put(expression, expr);
			}
			return expr.evaluate(new MapContext((Map<String, Object>) values));
		} catch (final Exception ex) {
			throw new ValidationException("Evaluating JEXL expression failed: " + expression, ex);
		}
	}
}