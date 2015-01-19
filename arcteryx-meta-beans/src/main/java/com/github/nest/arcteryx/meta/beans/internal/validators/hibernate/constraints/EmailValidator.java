/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * email validator
 * 
 * @author brad.wu
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
	private static final String SPECIAL_CHARACTERS = "'\\(\\)\\-\\.`";
	private static final String ASCII = "\\w " + SPECIAL_CHARACTERS;
	private static final String ASCII_WITHOUT_COMMA = "[" + ASCII + "]+";
	private static final String ASCII_WITH_COMMA = "\"[" + ASCII + ",]+\"";
	private static final String ASCII_WITH_QUESTION_MARK_AND_EQUALS = "[" + ASCII + "\\?\\=]+";
	private static final String MIME_ENCODED = "\\=\\?" + ASCII_WITH_QUESTION_MARK_AND_EQUALS + "\\?\\=";
	private static final String NAME = "(" + ASCII_WITHOUT_COMMA + "|" + ASCII_WITH_COMMA + "|" + MIME_ENCODED + ")";

	private static final String EMAIL_BASE_PATTERN = "['_A-Za-z0-9-&]+(\\.['_A-Za-z0-9-&]+)*[.]{0,1}@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))";

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^" + EMAIL_BASE_PATTERN + "$");

	private static final Pattern EMAIL_WITH_PERSONAL_NAME_PATTERN = Pattern.compile("^(" + EMAIL_BASE_PATTERN + "|"
			+ NAME + " +<" + EMAIL_BASE_PATTERN + ">)$");

	private boolean allowPersonalName = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Email constraintAnnotation) {
		this.setAllowPersonalName(constraintAnnotation.allowPersonalName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 *      javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;

		if (allowPersonalName)
			return EMAIL_WITH_PERSONAL_NAME_PATTERN.matcher(value).matches();
		return EMAIL_PATTERN.matcher(value.toString()).matches();
	}

	/**
	 * @return the allowPersonalName
	 */
	public boolean isAllowPersonalName() {
		return allowPersonalName;
	}

	/**
	 * @param allowPersonalName
	 *            the allowPersonalName to set
	 */
	public void setAllowPersonalName(boolean allowPersonalName) {
		this.allowPersonalName = allowPersonalName;
	}
}
