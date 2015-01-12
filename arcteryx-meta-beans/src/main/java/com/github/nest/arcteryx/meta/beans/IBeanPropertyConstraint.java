/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.internal.constraints.ConstraintSeverity;

/**
 * Bean property constraint
 * 
 * @author brad.wu
 */
public interface IBeanPropertyConstraint extends Serializable {
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
}
