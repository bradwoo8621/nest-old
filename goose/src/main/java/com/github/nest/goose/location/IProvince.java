/**
 * 
 */
package com.github.nest.goose.location;

import java.io.Serializable;

/**
 * province
 * 
 * @author brad.wu
 */
public interface IProvince extends Serializable {
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
