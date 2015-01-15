/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.Arrays;

/**
 * email constraint. pattern can be set or leave null. if the pattern is null,
 * use default pattern.
 * 
 * @author brad.wu
 */
public class Email extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 2931626251533090937L;

	private boolean allowPersonalName = false;

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

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalToString() + " [allowPersonalName=" + allowPersonalName + ", getMessageTemplate()="
				+ getMessageTemplate() + ", getWhen()=" + getWhen() + ", getTarget()=" + getTarget()
				+ ", getErrorCode()=" + getErrorCode() + ", getProfiles()=" + Arrays.toString(getProfiles())
				+ ", getSeverity()=" + getSeverity() + ", getAppliesTo()=" + getAppliesTo() + "]";
	}
}
