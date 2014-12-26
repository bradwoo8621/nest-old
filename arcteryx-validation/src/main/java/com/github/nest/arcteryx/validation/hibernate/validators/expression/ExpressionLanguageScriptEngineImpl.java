/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.validation.ValidationException;

import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ObjectCache;

/**
 * JSR223 Support. Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageScriptEngineImpl extends AbstractExpressionLanguage {
	private static final ScriptEngineManager FACTORY = new ScriptEngineManager();

	static {
		final List<Object> languages = new ArrayList<Object>();
		for (final ScriptEngineFactory ef : FACTORY.getEngineFactories()) {
			languages.add(ef.getNames());
		}
		LoggerFactory.getLogger(ExpressionLanguageScriptEngineImpl.class).info(
				"Available ScriptEngine language names: {1}", languages);
	}

	public static ExpressionLanguageScriptEngineImpl get(final String languageId) {
		final ScriptEngine engine = FACTORY.getEngineByName(languageId);
		return engine == null ? null : new ExpressionLanguageScriptEngineImpl(engine);
	}

	private final Compilable compilable;
	private final ScriptEngine engine;
	private final ObjectCache<String, CompiledScript> compiledCache;

	private ExpressionLanguageScriptEngineImpl(final ScriptEngine engine) {
		this.engine = engine;
		if (engine instanceof Compilable) {
			compilable = (Compilable) engine;
			compiledCache = new ObjectCache<String, CompiledScript>();
		} else {
			compilable = null;
			compiledCache = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating JavaScript expression: {1}", expression);
		try {
			final Bindings scope = engine.createBindings();
			for (final Entry<String, ?> entry : values.entrySet())
				scope.put(entry.getKey(), entry.getValue());

			if (compilable != null) {
				CompiledScript compiled = compiledCache.get(expression);
				if (compiled == null) {
					compiled = compilable.compile(expression);
					compiledCache.put(expression, compiled);
				}
				return compiled.eval(scope);
			}
			return engine.eval(expression, scope);
		} catch (final ScriptException ex) {
			throw new ValidationException("Evaluating JavaScript expression failed: " + expression, ex);
		}
	}
}