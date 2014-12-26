/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ValidationException;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ObjectCache;
import com.github.nest.arcteryx.validation.hibernate.validators.util.ThreadLocalObjectCache;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageGroovyImpl extends AbstractExpressionLanguage {
	private static final GroovyShell GROOVY_SHELL = new GroovyShell();

	private final ThreadLocalObjectCache<String, Script> threadScriptCache = new ThreadLocalObjectCache<String, Script>();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating Groovy expression: {1}", expression);
		try {
			final ObjectCache<String, Script> scriptCache = threadScriptCache.get();
			Script script = scriptCache.get(expression);
			if (script == null) {
				script = GROOVY_SHELL.parse(expression);
				scriptCache.put(expression, script);
			}

			final Binding binding = new Binding();
			for (final Entry<String, ?> entry : values.entrySet())
				binding.setVariable(entry.getKey(), entry.getValue());
			script.setBinding(binding);
			return script.run();
		} catch (final Exception ex) {
			throw new ValidationException("Evaluating script with Groovy failed.", ex);
		}
	}
}