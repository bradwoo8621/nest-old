/**
 * 
 */
package com.github.nest.goose.location;

import com.github.nest.goose.ICodeBaseBean;

/**
 * city
 * 
 * @author brad.wu
 */
public interface ICity extends ICodeBaseBean {
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
