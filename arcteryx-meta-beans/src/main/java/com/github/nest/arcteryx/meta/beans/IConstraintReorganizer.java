/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

/**
 * constraint reorganizer
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public interface IConstraintReorganizer<CC extends IConstraintContainer, C extends IConstraint> {
	/**
	 * get effective constraints
	 * 
	 * @param container
	 * @return
	 */
	List<C> getEffectiveConstraints(CC container);
}
