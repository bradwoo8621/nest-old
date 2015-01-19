/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.IBeanConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * bean constraint reorganizer
 * 
 * @author brad.wu
 */
public class BeanConstraintReorganizer extends AbstractConstraintReorganizer<IBeanDescriptor, IBeanConstraint>
		implements IBeanConstraintReorganizer {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.AbstractConstraintReorganizer#getAllConstraints(com.github.nest.arcteryx.meta.beans.IConstraintContainer)
	 */
	protected List<IBeanConstraint> getAllConstraints(IBeanDescriptor descriptor) {
		List<IBeanConstraint> list = new LinkedList<IBeanConstraint>();
		// get all constraints
		IBeanDescriptor parent = descriptor;
		while (parent != null) {
			if (parent != null && parent.getConstraint() != null) {
				list.addAll(parent.getConstraint().getConstraintsRecursive());
			}
			// do not get constraints from ancestors if overwrite is true
			if (this.isOverwrite()) {
				break;
			}
			parent = parent.getParent();
		}

		// now the constraints are sorted of myself to root.
		// remove the duplicated names. constraint in lowest level will be
		// effective.
		Map<String, IConstraint> names = new HashMap<String, IConstraint>();
		List<IBeanConstraint> newList = new LinkedList<IBeanConstraint>();
		for (int index = 0, count = list.size(); index < count; index++) {
			IBeanConstraint constraint = list.get(index);
			String name = constraint.getName();
			if (StringUtils.isNotEmpty(name)) {
				newList.add(constraint);
			} else {
				if (!names.containsKey(name)) {
					names.put(name, constraint);
					newList.add(constraint);
				}
			}
		}
		return newList;
	}
}