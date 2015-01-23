/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.constraints;

import java.util.Arrays;

import com.github.nest.arcteryx.meta.beans.annotation.Email;

/**
 * email constraint. pattern can be set or leave null. if the pattern is null,
 * use default pattern.
 * 
 * @author brad.wu
 */
public class EmailConstraint extends AbstractBeanPropertyConstraint<Email> {
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
	 * @see com.github.nest.arcteryx.meta.beans.constraints.AbstractBeanPropertyConstraint#configure(java.lang.annotation.Annotation)
	 */
	@Override
	public void configure(Email annotation) {
		super.configure(annotation);
		this.setAllowPersonalName(annotation.allowPersonalName());
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
