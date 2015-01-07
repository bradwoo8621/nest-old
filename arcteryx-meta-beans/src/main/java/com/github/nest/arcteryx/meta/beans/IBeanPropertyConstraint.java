/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;

/**
 * Bean property constraint
 * 
 * @author brad.wu
 */
public interface IBeanPropertyConstraint extends Serializable {
	/**
	 * get property descriptor
	 * 
	 * @return
	 */
	IBeanPropertyDescriptor getPropertyDescriptor();

	/**
	 * get bean descriptor
	 * 
	 * @return
	 */
	IBeanDescriptor getBeanDescriptor();
}
