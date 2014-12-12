/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IPropertyDescriptor;

/**
 * Bean property descriptor
 * 
 * @author brad.wu
 */
public interface IBeanPropertyDescriptor extends IPropertyDescriptor {
	/**
	 * get default value
	 * 
	 * @return
	 */
	Object getDefaultValue();

	/**
	 * get property constraint
	 * 
	 * @return
	 */
	IBeanPropertyConstraint getConstraint();
}
