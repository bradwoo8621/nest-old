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
	 * get name of constraint. name can be null, if don't want to be removed by
	 * name in sub classes.
	 * 
	 * @return
	 */
	String getName();

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

	/**
	 * get level of constraint
	 * 
	 * @return
	 */
	ConstraintLevel getLevel();
}
