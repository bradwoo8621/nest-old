/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * Not Null constraint.
 * 
 * @author brad.wu
 */
public class NotNull extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 3578613695715410091L;

	public NotNull(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}
}
