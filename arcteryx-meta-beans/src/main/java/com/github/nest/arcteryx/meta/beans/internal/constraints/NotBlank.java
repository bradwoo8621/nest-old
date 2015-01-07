/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * not blank constraint
 * 
 * @author brad.wu
 */
public class NotBlank extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 7499758139742453457L;

	public NotBlank(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}
}
