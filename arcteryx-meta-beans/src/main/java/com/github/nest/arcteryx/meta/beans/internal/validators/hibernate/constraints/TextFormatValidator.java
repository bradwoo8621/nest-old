/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * text format validator
 * 
 * @author brad.wu
 */
public class TextFormatValidator implements ConstraintValidator<TextFormat, Object> {
	private Pattern[] patterns = null;
	private boolean matchAll = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(TextFormat constraintAnnotation) {
		this.setPatterns(constraintAnnotation.patterns());
		this.setMatchAll(constraintAnnotation.matchAll());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null)
			return true;

		for (final Pattern p : this.getPatterns()) {
			final boolean matches = p.matcher(value.toString()).matches();

			if (matches) {
				if (!matchAll)
					return true;
			} else if (matchAll)
				return false;
		}
		return matchAll ? true : false;
	}

	/**
	 * @return the patterns
	 */
	public Pattern[] getPatterns() {
		return patterns;
	}

	/**
	 * @param patterns
	 *            the patterns to set
	 */
	public void setPatterns(String[] patterns) {
		assert patterns != null && patterns.length != 0 : "Patterns must be declared.";

		List<Pattern> list = new LinkedList<Pattern>();
		for (String pattern : patterns) {
			list.add(Pattern.compile(pattern));
		}
		this.patterns = list.toArray(new Pattern[list.size()]);
	}

	/**
	 * @return the matchAll
	 */
	public boolean isMatchAll() {
		return matchAll;
	}

	/**
	 * @param matchAll
	 *            the matchAll to set
	 */
	public void setMatchAll(boolean matchAll) {
		this.matchAll = matchAll;
	}
}
