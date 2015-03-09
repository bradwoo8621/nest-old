/**
 * 
 */
package com.github.nest.goose.location;

import java.io.Serializable;

/**
 * country
 * 
 * @author brad.wu
 */
public interface ICountry extends Serializable {
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
