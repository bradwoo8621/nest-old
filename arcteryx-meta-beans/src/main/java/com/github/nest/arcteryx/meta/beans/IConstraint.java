/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

/**
 * constraint
 * 
 * @author brad.wu
 */
public interface IConstraint {
	/**
	 * get constraints recursive
	 * 
	 * @return
	 */
	<T extends IConstraint> List<T> getConstraintsRecursive();

	/**
	 * get severity of constraint
	 * 
	 * @return
	 */
	ConstraintSeverity getSeverity();

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
}
