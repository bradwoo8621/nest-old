/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

/**
 * constraint severity
 * 
 * @author brad.wu
 */
public enum ConstraintSeverity {
	FATAL(8), ERROR(4), WARN(2), INFO(1);

	private int severity = 1;

	private ConstraintSeverity(final int severity) {
		this.severity = severity;
	}

	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return severity;
	}
}
