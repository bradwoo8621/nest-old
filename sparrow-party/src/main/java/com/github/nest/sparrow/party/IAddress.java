/**
 * 
 */
package com.github.nest.sparrow.party;

import com.github.nest.sparrow.enums.define.ICity;
import com.github.nest.sparrow.enums.define.ICountry;
import com.github.nest.sparrow.enums.define.IDistrict;
import com.github.nest.sparrow.enums.define.IProvince;

/**
 * party address interface
 * 
 * @author brad.wu
 */
public interface IAddress {
	/**
	 * get postcode
	 * 
	 * @return
	 */
	String getPostcode();

	/**
	 * get district
	 * 
	 * @return
	 */
	IDistrict getDistrict();

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

	/**
	 * get address line 1
	 * 
	 * @return
	 */
	String getAddressLine1();

	/**
	 * get address line 2
	 * 
	 * @return
	 */
	String getAddressLine2();

	/**
	 * get address line 3
	 * 
	 * @return
	 */
	String getAddressLine3();

	/**
	 * get address line 4
	 * 
	 * @return
	 */
	String getAddressLine4();

	/**
	 * get address line 5
	 * 
	 * @return
	 */
	String getAddressLine5();

	/**
	 * get telephone number
	 * 
	 * @return
	 */
	String getTelephone();

	/**
	 * get cell phone number
	 * 
	 * @return
	 */
	String getCellphone();

	/**
	 * get fax number
	 * 
	 * @return
	 */
	String getFax();

	/**
	 * get email
	 * 
	 * @return
	 */
	String getEmail();

	/**
	 * get post office box number
	 * 
	 * @return
	 */
	String getPOBox();
}
