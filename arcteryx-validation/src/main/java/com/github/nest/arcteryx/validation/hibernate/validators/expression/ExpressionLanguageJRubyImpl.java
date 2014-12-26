/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ValidationException;

import org.jruby.CompatVersion;
import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public class ExpressionLanguageJRubyImpl extends AbstractExpressionLanguage {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.validators.expression.ExpressionLanguage#evaluate(java.lang.String,
	 *      java.util.Map)
	 */
	public Object evaluate(final String expression, final Map<String, ?> values) {
		getLogger().debug("Evaluating JRuby expression: {1}", expression);
		try {
			final RubyInstanceConfig config = new RubyInstanceConfig();
			config.setCompatVersion(CompatVersion.RUBY1_9);
			final Ruby runtime = JavaEmbedUtils.initialize(new ArrayList<String>(), config);

			final StringBuilder localVars = new StringBuilder();
			for (final Entry<String, ?> entry : values.entrySet()) {
				runtime.getGlobalVariables().set("$" + entry.getKey(),
						JavaEmbedUtils.javaToRuby(runtime, entry.getValue()));
				localVars.append(entry.getKey()) //
						.append("=$") //
						.append(entry.getKey()) //
						.append("\n");
			}
			final IRubyObject result = runtime.evalScriptlet(localVars + expression);
			return JavaEmbedUtils.rubyToJava(runtime, result, Object.class);
		} catch (final RuntimeException ex) {
			throw new ValidationException("Evaluating JRuby expression failed: " + expression, ex);
		}
	}
}