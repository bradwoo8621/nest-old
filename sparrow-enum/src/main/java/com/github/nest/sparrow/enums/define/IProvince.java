/**
 * 
 */
package com.github.nest.sparrow.enums.define;

import java.util.List;

import com.github.nest.sparrow.enums.ICodedEnumItem;

/**
 * province interface
 * 
 * @author brad.wu
 */
public interface IProvince extends ICodedEnumItem {
	/**
	 * get country
	 * 
	 * @return
	 */
	ICountry getCountry();

	/**
	 * get cities
	 * 
	 * @return
	 */
	List<ICity> getCities();

	/**
	 * add city
	 * 
	 * @param city
	 */
	void addCity(ICity city);

	/**
	 * get abbreviation
	 * 
	 * @return
	 */
	String getAbbreviation();
}
