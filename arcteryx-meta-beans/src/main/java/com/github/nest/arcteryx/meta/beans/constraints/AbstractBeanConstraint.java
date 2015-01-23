/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.constraints;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanConstraint;

/**
 * abstract bean constraint
 * 
 * @author brad.wu
 */
public abstract class AbstractBeanConstraint<ConstraintAnnotatoin extends Annotation> extends
		AbstractConstraint<ConstraintAnnotatoin> implements IBeanConstraint<ConstraintAnnotatoin> {
	private static final long serialVersionUID = 1279763956572608181L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getConstraintsRecursive()
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List<IBeanConstraint> getConstraintsRecursive() {
		List<IBeanConstraint> list = new LinkedList<IBeanConstraint>();
		list.add(this);
		return list;
	}
}
