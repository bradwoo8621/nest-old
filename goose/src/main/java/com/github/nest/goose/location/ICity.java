/**
 * 
 */
package com.github.nest.goose.location;

import java.io.Serializable;

/**
 * city
 * 
 * @author brad.wu
 */
public interface ICity extends Serializable {
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

	/**
	 * get province code
	 * 
	 * @return
	 */
	String getProvinceCode();

	/**
	 * get country code
	 * 
	 * @return
	 */
	String getCountryCode();
}
