/**
 * 
 */
package com.github.nest.sparrow.enums.define;

import java.util.List;

import com.github.nest.sparrow.enums.ICodedEnumItem;

/**
 * country
 * 
 * @author brad.wu
 */
public interface ICountry extends ICodedEnumItem {
	/**
	 * get provinces of country
	 * 
	 * @return
	 */
	List<IProvince> getProvinces();

	/**
	 * add province
	 * 
	 * @param province
	 */
	void addProvince(IProvince province);

	/**
	 * get abbreviation
	 * 
	 * @return
	 */
	String getAbbreviation();
}
