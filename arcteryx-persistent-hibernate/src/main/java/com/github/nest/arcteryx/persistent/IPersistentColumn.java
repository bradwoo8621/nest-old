/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.io.Serializable;

/**
 * persistent column
 * 
 * @author brad.wu
 */
public interface IPersistentColumn extends Serializable {
	/**
	 * get property descriptor
	 * 
	 * @return
	 */
	IPersistentBeanPropertyDescriptor getPropertyDescriptor();

	/**
	 * set property descriptor
	 * 
	 * @param propertyDescriptor
	 */
	void setPropertyDescriptor(IPersistentBeanPropertyDescriptor propertyDescriptor);
}
