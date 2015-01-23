/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * constraint container
 * 
 * @author brad.wu
 */
public interface IConstraintContainer {
	/**
	 * get constraint
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	<T extends IConstraint> T getConstraint();
}
