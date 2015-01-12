/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Bean property constraint
 * 
 * @author brad.wu
 */
public interface IBeanPropertyConstraint extends Serializable {
	/**
	 * severity of constraint
	 * 
	 * @author brad.wu
	 */
	public enum Severity {
		FATAL(8), ERROR(4), WARN(2), INFO(1);

		private int severity = 1;

		private Severity(final int severity) {
			this.severity = severity;
		}

		/**
		 * @return the severity
		 */
		public int getSeverity() {
			return severity;
		}
	}

	/**
	 * target which constraint applies to. default is {@linkplain #VALUES}
	 * 
	 * @author brad.wu
	 */
	public enum ApplyTo {
		KEYS, VALUES, CONTAINER
	}

	/**
	 * get constraints recursive
	 * 
	 * @return
	 */
	List<IBeanPropertyConstraint> getConstraintsRecursive();

	/**
	 * get severity of constraint
	 * 
	 * @return
	 */
	Severity getSeverity();

	/**
	 * get profiles of constraint
	 * 
	 * @return
	 */
	String[] getProfiles();

	/**
	 * get error code of constraint
	 * 
	 * @return
	 */
	String getErrorCode();

	/**
	 * get target of constraint
	 * 
	 * @return
	 */
	String getTarget();

	/**
	 * get when of constraint
	 * 
	 * @return
	 */
	String getWhen();

	/**
	 * get message template of constraint
	 * 
	 * @return
	 */
	String getMessageTemplate();

	/**
	 * get applies to
	 * 
	 * @return
	 */
	ApplyTo getAppliesTo();
}
