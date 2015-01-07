/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * text format constraint
 * 
 * @author brad.wu
 */
public class TextFormat extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -1615346046005948029L;

	private String pattern = null;

	public TextFormat(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

	/**
	 * @return the pattern
	 */
	protected String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	protected void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
