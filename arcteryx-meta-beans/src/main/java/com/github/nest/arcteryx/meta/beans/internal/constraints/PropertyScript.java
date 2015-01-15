/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.Arrays;

/**
 * script constraint. script contains two parts, language and script body. eg.
 * groovy:_this.emailAddress != null.
 * 
 * @author brad.wu
 */
public class PropertyScript extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -2064084972261552830L;

	private String script = null;

	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	public void setScript(String script) {
		this.script = script;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalToString() + " [script=" + script + ", getMessageTemplate()=" + getMessageTemplate()
				+ ", getWhen()=" + getWhen() + ", getTarget()=" + getTarget() + ", getErrorCode()=" + getErrorCode()
				+ ", getProfiles()=" + Arrays.toString(getProfiles()) + ", getSeverity()=" + getSeverity()
				+ ", getAppliesTo()=" + getAppliesTo() + "]";
	}
}
