/**
 * 
 */
package com.github.nest.sparrow.party.codes;

import java.io.Serializable;

/**
 * academic major in higher education or third-level education.
 * 
 * @author brad.wu
 */
public interface IAcademicMajor extends Serializable {
	/**
	 * get code
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * get name
	 * 
	 * @return
	 */
	String getName();
}
