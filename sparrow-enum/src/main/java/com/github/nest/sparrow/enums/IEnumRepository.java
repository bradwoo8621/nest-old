/**
 * 
 */
package com.github.nest.sparrow.enums;

/**
 * enumeration repository
 * 
 * @author brad.wu
 */
public interface IEnumRepository {
	/**
	 * get enumeration definition list by given code
	 * 
	 * @param code
	 * @return
	 */
	IEnum get(String code);
}
