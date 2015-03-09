/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;

/**
 * education degree
 * 
 * @author brad.wu
 */
public interface IEducationDegree extends Serializable {
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
