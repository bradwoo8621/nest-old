/**
 * 
 */
package com.github.nest.sparrow.enums;

import java.io.Serializable;

/**
 * enumeration interface
 * 
 * @author brad.wu
 */
public interface IEnumItem extends Serializable {
	/**
	 * get identity of enumeration item
	 * 
	 * @return
	 */
	String getId();

	/**
	 * get label
	 * 
	 * @return
	 */
	String getLabel();
}
