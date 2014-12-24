/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.constraint;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;

/**
 * @author brad.wu
 */
public class UpperCaseCheck extends AbstractAnnotationCheck<UpperCase> {
	private static final long serialVersionUID = 1L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.Check#isSatisfied(java.lang.Object, java.lang.Object,
	 *      net.sf.oval.context.OValContext, net.sf.oval.Validator)
	 */
	public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) {
		if (valueToValidate == null)
			return true;

		String val = valueToValidate.toString();

		return val.equals(val.toUpperCase());
	}
}