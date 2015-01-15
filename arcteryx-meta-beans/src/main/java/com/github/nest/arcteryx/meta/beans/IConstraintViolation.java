/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

/**
 * bean constraint violation
 * 
 * @author brad.wu
 */
public interface IConstraintViolation {
	/**
	 * get error code
	 * 
	 * @return
	 */
	String getErrorCode();

	/**
	 * get message
	 * 
	 * @return
	 */
	String getMessage();

	/**
	 * get message template
	 * 
	 * @return
	 */
	String getMessageTemplate();

	/**
	 * get value to validate
	 * 
	 * @return
	 */
	<T> T getValueToValidate();

	/**
	 * get bean to validate
	 * 
	 * @return
	 */
	<T> T getBeanToValidate();

	/**
	 * get severity
	 * 
	 * @return
	 */
	ConstraintSeverity getConstraintSeverity();

	/**
	 * get causes
	 * 
	 * @return
	 */
	List<IConstraintViolation> getConstraintCauses();
}
