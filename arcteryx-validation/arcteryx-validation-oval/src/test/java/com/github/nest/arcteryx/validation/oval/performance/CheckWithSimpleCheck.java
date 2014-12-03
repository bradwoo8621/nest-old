/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.performance;

import net.sf.oval.constraint.CheckWithCheck.SimpleCheck;

/**
 * @author brad.wu
 *
 */
public class CheckWithSimpleCheck implements SimpleCheck {
	private static final long serialVersionUID = 1L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.constraint.CheckWithCheck.SimpleCheck#isSatisfied(java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public boolean isSatisfied(Object validatedObject, Object value) {
		return false;
	}
}
