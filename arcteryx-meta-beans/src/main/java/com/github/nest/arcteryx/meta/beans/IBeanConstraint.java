/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Bean constraint
 * 
 * @author brad.wu
 */
public interface IBeanConstraint<ConstraintAnnotatoin extends Annotation> extends IConstraint<ConstraintAnnotatoin>,
		Serializable {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IConstraint#getConstraintsRecursive()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	List<IBeanConstraint> getConstraintsRecursive();
}