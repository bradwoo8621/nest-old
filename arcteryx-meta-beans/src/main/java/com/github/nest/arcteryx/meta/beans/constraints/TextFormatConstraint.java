/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.constraints;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.annotation.TextFormat;

/**
 * text format constraint
 * 
 * @author brad.wu
 */
public class TextFormatConstraint extends AbstractBeanPropertyConstraint<TextFormat> {
	private static final long serialVersionUID = -1615346046005948029L;

	private String[] patterns = null;
	private boolean matchAll = false;

	/**
	 * @return the patterns
	 */
	public String[] getPatterns() {
		return patterns;
	}

	/**
	 * @param patterns
	 *            the patterns to set
	 */
	public void setPatterns(String[] patterns) {
		this.patterns = patterns;
	}

	/**
	 * set pattern
	 * 
	 * @param pattern
	 */
	public void setPattern(String pattern) {
		assert StringUtils.isNotBlank(pattern) : "Pattern cannot be empty string.";

		this.patterns = new String[] { pattern };
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.constraints.AbstractBeanPropertyConstraint#configure(java.lang.annotation.Annotation)
	 */
	@Override
	public void configure(TextFormat annotation) {
		super.configure(annotation);
		this.setPatterns(annotation.patterns());
		this.setMatchAll(annotation.matchAll());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalToString() + " [patterns=" + Arrays.toString(patterns) + ", matchAll=" + matchAll
				+ ", getMessageTemplate()=" + getMessageTemplate() + ", getWhen()=" + getWhen() + ", getTarget()="
				+ getTarget() + ", getErrorCode()=" + getErrorCode() + ", getProfiles()="
				+ Arrays.toString(getProfiles()) + ", getSeverity()=" + getSeverity() + ", getAppliesTo()="
				+ getAppliesTo() + "]";
	}
}
