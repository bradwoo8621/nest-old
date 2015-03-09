/**
 * 
 */
package com.github.nest.goose.location;

import java.io.Serializable;

/**
 * district
 * 
 * @author brad.wu
 */
public interface IDistrict extends Serializable {
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
