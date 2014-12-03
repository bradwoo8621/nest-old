/**
 * 
 */
package com.github.nest.sparrow.enums.define;

import java.util.List;

import com.github.nest.sparrow.enums.ICodedEnumItem;

/**
 * city interface
 * 
 * @author brad.wu
 */
public interface ICity extends ICodedEnumItem {
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

	/**
	 * get districts
	 * 
	 * @return
	 */
	List<IDistrict> getDistricts();

	/**
	 * add district
	 * 
	 * @param district
	 */
	void addDistrict(IDistrict district);

	/**
	 * get abbreviation
	 * 
	 * @return
	 */
	String getAbbreviation();
}
