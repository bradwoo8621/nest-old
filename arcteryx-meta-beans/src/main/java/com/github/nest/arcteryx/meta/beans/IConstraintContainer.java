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
	<T extends IConstraint> T getConstraint();
}
