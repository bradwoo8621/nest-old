/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval;

import net.sf.oval.Check;

import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * OVal check convertor
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public interface IOValCheckConvertor<C extends IConstraint> {
	/**
	 * convert property contraint to OVal check
	 * 
	 * @param constraint
	 * @return
	 */
	Check convert(C constraint);

	/**
	 * get supported constraint type
	 * 
	 * @return
	 */
	Class<C> getSupportedConstraintType();
}
