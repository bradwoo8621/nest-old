/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * persistent bean property descriptor
 * 
 * @author brad.wu
 */
public interface IPersistentBeanPropertyDescriptor extends IBeanPropertyDescriptor {
	/**
	 * get persistent column
	 * 
	 * @return
	 */
	IPersistentColumn getPersistentColumn();
}
