/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * bean property constraint reorganizer
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public class BeanPropertyConstraintReorganizer extends
		AbstractConstraintReorganizer<IBeanPropertyDescriptor, IBeanPropertyConstraint> implements
		IBeanPropertyConstraintReorganizer {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.AbstractConstraintReorganizer#getAllConstraints(com.github.nest.arcteryx.meta.beans.IConstraintContainer)
	 */
	@SuppressWarnings("unchecked")
	protected List<IBeanPropertyConstraint> getAllConstraints(IBeanPropertyDescriptor descriptor) {
		List<IBeanPropertyConstraint> list = new LinkedList<IBeanPropertyConstraint>();

		// get all constraints
		List<IBeanDescriptor> descriptors = descriptor.getBeanDescriptor().getBeanDescriptorContext()
				.getRecursive(descriptor.getBeanDescriptor().getBeanClass());
		String propertyName = descriptor.getName();
		for (IBeanDescriptor bean : descriptors) {
			IBeanPropertyDescriptor property = bean.getDeclaredProperty(propertyName);
			if (property != null && property.getConstraint() != null) {
				list.addAll(property.getConstraint().getConstraintsRecursive());
			}
			if (this.isOverwrite()) {
				break;
			}
		}

		// now the constraints are sorted of myself to root.
		// remove the duplicated names. constraint in lowest level will be
		// effective.
		Map<String, IConstraint> names = new HashMap<String, IConstraint>();
		List<IBeanPropertyConstraint> newList = new LinkedList<IBeanPropertyConstraint>();
		for (int index = 0, count = list.size(); index < count; index++) {
			IBeanPropertyConstraint constraint = list.get(index);
			String name = constraint.getName();
			if (StringUtils.isEmpty(name)) {
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
