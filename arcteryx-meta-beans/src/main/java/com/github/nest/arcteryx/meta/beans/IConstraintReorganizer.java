/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;
import java.util.List;

/**
 * constraint reorganizer
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public interface IConstraintReorganizer<CC extends IConstraintContainer, C extends IConstraint> extends Serializable {
	/**
	 * get effective constraints
	 * 
	 * @param container
	 * @return
	 */
	List<C> getEffectiveConstraints(CC container);
}
