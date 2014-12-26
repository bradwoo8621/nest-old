/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ValidationException;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ObjectCache;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageOGNLImpl extends AbstractExpressionLanguage {
	private final ObjectCache<String, Object> expressionCache = new ObjectCache<String, Object>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating OGNL expression: {1}", expression);
		try {
			final OgnlContext ctx = (OgnlContext) Ognl.createDefaultContext(null);

			for (final Entry<String, ?> entry : values.entrySet())
				ctx.put(entry.getKey(), entry.getValue());

			Object expr = expressionCache.get(expression);
			if (expr == null) {
				expr = Ognl.parseExpression(expression);
				expressionCache.put(expression, expr);
			}
			return Ognl.getValue(expr, ctx);
		} catch (final OgnlException ex) {
			throw new ValidationException("Evaluating MVEL expression failed: " + expression, ex);
		}
	}
}