package com.github.nest.arcteryx.meta.beans;

/**
 * severity of constraint
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

	/**
	 * convert severity value to enumeration definition
	 * 
	 * @param severity
	 * @return
	 */
	public static ConstraintSeverity convert(int severity) {
		switch (severity) {
		case 1:
			return INFO;
		case 2:
			return WARN;
		case 4:
			return ERROR;
		case 8:
			return FATAL;
		default:
			return defaultSeverity();
		}
	}

	/**
	 * get default severity
	 * 
	 * @return
	 */
	public static ConstraintSeverity defaultSeverity() {
		return ERROR;
	}
}