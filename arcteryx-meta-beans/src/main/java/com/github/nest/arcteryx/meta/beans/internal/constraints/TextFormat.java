/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;


/**
 * text format constraint
 * 
 * @author brad.wu
 */
public class TextFormat extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -1615346046005948029L;

	private String pattern = null;

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
