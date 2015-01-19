/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import org.hibernate.validator.cfg.ConstraintDef;

import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * hibernate constraint convertor
 * 
 * @author brad.wu
 */
public interface IHibernateConstraintConvertor<C extends IConstraint> {
	/**
	 * convert constraint to hibernate format
	 * 
	 * @param constraint
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	ConstraintDef convert(C constraint);

	/**
	 * get supported constraint type
	 * 
	 * @return
	 */
	Class<C> getSupportedConstraintType();
}
