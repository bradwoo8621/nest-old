/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import static net.sf.oval.Validator.getCollectionFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

/**
 * text format check
 * 
 * @author brad.wu
 */
public class TextFormatCheck extends AbstractAnnotationCheck<TextFormat> {
	private static final long serialVersionUID = -8009682334963301803L;

	private Pattern pattern = null;
	private String format = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.Check#isSatisfied(java.lang.Object, java.lang.Object,
	 *      net.sf.oval.context.OValContext, net.sf.oval.Validator)
	 */
	@Override
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator)
			throws OValException {
		if (valueToValidate == null)
			return true;

		Matcher matcher = this.getPattern().matcher(valueToValidate.toString());
		return matcher.matches();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.configuration.annotation.AbstractAnnotationCheck#configure(java.lang.annotation.Annotation)
	 */
	@Override
	public void configure(TextFormat constraintAnnotation) {
		super.configure(constraintAnnotation);
		setFormat(constraintAnnotation.format());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.AbstractCheck#createMessageVariables()
	 */
	@Override
	protected Map<String, ? extends Serializable> createMessageVariables() {
		final Map<String, String> messageVariables = getCollectionFactory().createMap(1);
		messageVariables.put("format", format);
		return messageVariables;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the pattern
	 */
	protected Pattern getPattern() {
		if (this.pattern == null) {
			synchronized (this) {
				if (this.pattern == null) {
					this.pattern = Pattern.compile(this.getFormat());
				}
			}
		}
		return pattern;
	}
}
