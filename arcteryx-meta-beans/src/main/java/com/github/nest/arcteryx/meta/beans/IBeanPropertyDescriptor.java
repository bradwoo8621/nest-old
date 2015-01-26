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
public interface IBeanPropertyDescriptor extends IPropertyDescriptor, IConstraintContainer {
	/**
	 * get default value
	 * 
	 * @return
	 */
	Object getDefaultValue();

	/**
	 * get default value format
	 * 
	 * @return
	 */
	String getDefaultValueFormat();

	/**
	 * get property constraint
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	IBeanPropertyConstraint getConstraint();

	/**
	 * get constraint reorganizer
	 * 
	 * @return
	 */
	IBeanPropertyConstraintReorganizer getConstraintReorganizer();

	/**
	 * get bean descriptor
	 * 
	 * @return
	 */
	IBeanDescriptor getBeanDescriptor();
}
