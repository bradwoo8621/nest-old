/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Bean property constraint
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public interface IBeanPropertyConstraint<ConstraintAnnotatoin extends Annotation> extends
		IConstraint<ConstraintAnnotatoin>, Serializable {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#getConstraintsRecursive()
	 */
	@SuppressWarnings("unchecked")
	@Override
	List<IBeanPropertyConstraint> getConstraintsRecursive();

	/**
	 * get target of constraint
	 * 
	 * @return
	 */
	String getTarget();

	/**
	 * get applies to
	 * 
	 * @return
	 */
	ConstraintApplyTo getAppliesTo();
}
