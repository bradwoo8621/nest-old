/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.expression;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.validation.hibernate.validators.util.ClassUtil;

/**
 * Copy from OVal
 * 
 * @author brad.wu
 */
public final class ExpressionLanguageRegistry {
	public static final String EL_JEXL = "jexl";
	public static final String EL_RUBY = "ruby";
	public static final String EL_JRUBY = "jruby";
	public static final String EL_MVEL = "mvel";
	public static final String EL_OGNL = "ognl";
	public static final String EL_BSH = "bsh";
	public static final String EL_BEANSHELL = "beanshell";
	public static final String EL_GROOVY = "groovy";
	public static final String EL_JS = "js";
	public static final String EL_JAVASCRIPT = "javascript";

	public static Logger logger = LoggerFactory.getLogger(ExpressionLanguageRegistry.class);

	private final static Map<String, ExpressionLanguage> elcache = new LinkedHashMap<String, ExpressionLanguage>();

	private static ExpressionLanguage initializeDefaultEL(final String languageId) {
		// JavaScript support
		if ((EL_JAVASCRIPT.equals(languageId) || EL_JS.equals(languageId))
				&& ClassUtil.isClassPresent("org.mozilla.javascript.Context"))
			return registerExpressionLanguage(EL_JS,
					registerExpressionLanguage(EL_JAVASCRIPT, new ExpressionLanguageJavaScriptImpl()));

		// Groovy support
		if (EL_GROOVY.equals(languageId) && ClassUtil.isClassPresent("groovy.lang.Binding"))
			return registerExpressionLanguage(EL_GROOVY, new ExpressionLanguageGroovyImpl());

		// BeanShell support
		if ((EL_BEANSHELL.equals(languageId) || EL_BSH.equals(languageId))
				&& ClassUtil.isClassPresent("bsh.Interpreter"))
			return registerExpressionLanguage(EL_BEANSHELL,
					registerExpressionLanguage(EL_BSH, new ExpressionLanguageBeanShellImpl()));

		// OGNL support
		if (EL_OGNL.equals(languageId) && ClassUtil.isClassPresent("ognl.Ognl"))
			return registerExpressionLanguage(EL_OGNL, new ExpressionLanguageOGNLImpl());

		// MVEL2 support
		if (EL_MVEL.equals(languageId) && ClassUtil.isClassPresent("org.mvel2.MVEL"))
			return registerExpressionLanguage(EL_MVEL, new ExpressionLanguageMVELImpl());

		// JRuby support
		else if ((EL_JRUBY.equals(languageId) || EL_RUBY.equals(languageId))
				&& ClassUtil.isClassPresent("org.jruby.Ruby"))
			return registerExpressionLanguage(EL_JRUBY,
					registerExpressionLanguage(EL_RUBY, new ExpressionLanguageJRubyImpl()));

		// JEXL2 support
		if (EL_JEXL.equals(languageId) && ClassUtil.isClassPresent("org.apache.commons.jexl2.JexlEngine"))
			return registerExpressionLanguage(EL_JEXL, new ExpressionLanguageJEXLImpl());

		// scripting support via JSR223
		if (ClassUtil.isClassPresent("javax.script.ScriptEngineManager")) {
			final ExpressionLanguage el = ExpressionLanguageScriptEngineImpl.get(languageId);
			if (el != null)
				return registerExpressionLanguage(languageId, el);
		}

		return null;
	}

	/**
	 *
	 * @param languageId
	 *            the id of the language, cannot be null
	 *
	 * @throws IllegalArgumentException
	 *             if <code>languageName == null</code>
	 * @throws ExpressionLanguageNotAvailableException
	 */
	public static ExpressionLanguage getExpressionLanguage(final String languageId) {
		assert languageId != null : "Language ID cannot be null.";

		ExpressionLanguage el = elcache.get(languageId);

		if (el == null) {
			synchronized (elcache) {
				el = elcache.get(languageId);
				if (el == null) {
					el = initializeDefaultEL(languageId);
				}
			}
		}

		if (el == null)
			throw new ValidationException(languageId);

		return el;
	}

	/**
	 *
	 * @param languageId
	 *            the expression language identifier
	 * @param impl
	 *            the expression language implementation
	 * @throws IllegalArgumentException
	 *             if
	 *             <code>languageId == null || expressionLanguage == null</code>
	 */
	public static ExpressionLanguage registerExpressionLanguage(final String languageId, final ExpressionLanguage impl)
			throws IllegalArgumentException {
		assert languageId != null : "Language ID cannot be null.";
		assert impl != null : "Language implementation cannot be null.";

		getLogger().info("Expression language '{1}' registered: {2}", languageId, impl);
		elcache.put(languageId, impl);
		return impl;
	}

	/**
	 * @return the logger
	 */
	protected static Logger getLogger() {
		return logger;
	}
}
