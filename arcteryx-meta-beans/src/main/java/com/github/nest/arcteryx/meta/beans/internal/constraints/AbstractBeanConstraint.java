/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.IBeanConstraint;

/**
 * abstract bean constraint
 * 
 * @author brad.wu
 */
public class AbstractBeanConstraint extends AbstractConstraint implements IBeanConstraint {
	private static final long serialVersionUID = 1279763956572608181L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint#getConstraintsRecursive()
	 */
	@Override
	public List<IBeanConstraint> getConstraintsRecursive() {
		List<IBeanConstraint> list = new LinkedList<IBeanConstraint>();
		list.add(this);
		return list;
	}
}
