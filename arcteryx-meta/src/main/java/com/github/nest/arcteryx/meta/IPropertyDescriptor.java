/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.io.Serializable;

/**
 * Property of resource interface
 * 
 * @author brad.wu
 */
public interface IPropertyDescriptor extends Serializable {
	/**
	 * get name of property
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get description
	 * 
	 * @return
	 */
	String getDescription();
}
