/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ValidationException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ObjectCache;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageJavaScriptImpl extends AbstractExpressionLanguage {
	private final Scriptable parentScope;

	private final ObjectCache<String, Script> scriptCache = new ObjectCache<String, Script>();

	/**
	 * Default constructor.
	 */
	public ExpressionLanguageJavaScriptImpl() {
		final Context ctx = ContextFactory.getGlobal().enterContext();
		try {
			parentScope = ctx.initStandardObjects();
		} finally {
			Context.exit();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	public Object evaluate(final String expression, final Map<String, ?> values)  {
		getLogger().debug("Evaluating JavaScript expression: {1}", expression);
		try {
			final Context ctx = ContextFactory.getGlobal().enterContext();
			Script script = scriptCache.get(expression);
			if (script == null) {
				ctx.setOptimizationLevel(9);
				script = ctx.compileString(expression, "<cmd>", 1, null);
				scriptCache.put(expression, script);
			}
			final Scriptable scope = ctx.newObject(parentScope);
			scope.setPrototype(parentScope);
			scope.setParentScope(null);
			for (final Entry<String, ?> entry : values.entrySet())
				scope.put(entry.getKey(), scope, Context.javaToJS(entry.getValue(), scope));
			return script.exec(ctx, scope);
		} catch (final EvaluatorException ex) {
			throw new ValidationException("Evaluating JavaScript expression failed: " + expression, ex);
		} finally {
			Context.exit();
		}
	}
}