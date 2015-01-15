/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Bean constraint
 * 
 * @author brad.wu
 */
public interface IBeanConstraint extends IConstraint, Serializable {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#getConstraintsRecursive()
	 */
	@SuppressWarnings("unchecked")
	@Override
	List<IBeanConstraint> getConstraintsRecursive();
}