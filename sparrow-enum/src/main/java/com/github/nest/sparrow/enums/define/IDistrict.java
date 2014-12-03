/**
 * 
 */
package com.github.nest.sparrow.enums.define;

import com.github.nest.sparrow.enums.IEnumItem;

/**
 * district interface
 * 
 * @author brad.wu
 */
public interface IDistrict extends IEnumItem {
	/**
	 * get city
	 * 
	 * @return
	 */
	ICity getCity();

	/**
	 * get province
	 * 
	 * @return
	 */
	IProvince getProvince();

	/**
	 * get country
	 * 
	 * @return
	 */
	ICountry getCountry();
}
