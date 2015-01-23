/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * constraint reorganizer. which can remove constraint without change the
 * constraint settings.<br>
 * all implementation should contains a constructor without parameters.
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public interface IBeanConstraintReorganizer extends IConstraintReorganizer<IBeanDescriptor, IBeanConstraint> {
}
