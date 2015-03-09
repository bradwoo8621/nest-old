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
}
