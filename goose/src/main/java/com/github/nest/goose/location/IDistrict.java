/**
 * 
 */
package com.github.nest.goose.location;

import com.github.nest.goose.ICodeBaseBean;

/**
 * district
 * 
 * @author brad.wu
 */
public interface IDistrict extends ICodeBaseBean {
	/**
	 * get name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get city code
	 * 
	 * @return
	 */
	String getCityCode();

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
