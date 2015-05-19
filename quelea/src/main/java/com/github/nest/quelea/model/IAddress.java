/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;

import com.github.nest.quelea.codes.AddressType;
import com.github.nest.quelea.codes.City;
import com.github.nest.quelea.codes.Country;
import com.github.nest.quelea.codes.District;
import com.github.nest.quelea.codes.Province;

/**
 * address
 * 
 * @author brad.wu
 */
public interface IAddress extends Serializable {
	/**
	 * get address id
	 * 
	 * @return
	 */
	Long getAddressId();

	/**
	 * set address id
	 * 
	 * @param addressId
	 */
	void setAddressId(Long addressId);

	/**
	 * get country/region code
	 * 
	 * @return
	 * @see Country
	 */
	String getCountryCode();

	/**
	 * set country/region code
	 * 
	 * @param countryCode
	 * @see Country
	 */
	void setCountryCode(String countryCode);

	/**
	 * get province/state code
	 * 
	 * @return
	 * @see Province
	 */
	String getProvinceCode();

	/**
	 * set province/state code
	 * 
	 * @param provinceCode
	 * @see Province
	 */
	void setProvinceCode(String provinceCode);

	/**
	 * get city code
	 * 
	 * @return
	 * @see City
	 */
	String getCityCode();

	/**
	 * set city code
	 * 
	 * @param cityCode
	 * @see City
	 */
	void setCityCode(String cityCode);

	/**
	 * get district code
	 * 
	 * @return
	 * @see District
	 */
	String getDistrictCode();

	/**
	 * set district code
	 * 
	 * @param districtCode
	 * @see District
	 */
	void setDistrictCode(String districtCode);

	/**
	 * get postcode
	 * 
	 * @return
	 */
	String getPostcode();

	/**
	 * set postcode
	 * 
	 * @param postcode
	 */
	void setPostcode(String postcode);

	/**
	 * get address line1
	 * 
	 * @return
	 */
	String getLine1();

	/**
	 * get address line1
	 * 
	 * @param line1
	 */
	void setLine1(String line1);

	/**
	 * get address line2
	 * 
	 * @return
	 */
	String getLine2();

	/**
	 * set address line2
	 * 
	 * @param line2
	 */
	void setLine2(String line2);

	/**
	 * get address line3
	 * 
	 * @return
	 */
	String getLine3();

	/**
	 * set address line3
	 * 
	 * @param line3
	 */
	void setLine3(String line3);

	/**
	 * get address line4
	 * 
	 * @return
	 */
	String getLine4();

	/**
	 * set address line4
	 * 
	 * @param line4
	 */
	void setLine4(String line4);

	/**
	 * get address line5
	 * 
	 * @return
	 */
	String getLine5();

	/**
	 * set address line5
	 * 
	 * @param line5
	 */
	void setLine5(String line5);

	/**
	 * get address type code
	 * 
	 * @return
	 * @see AddressType
	 */
	String getAddressTypeCode();

	/**
	 * set address type code
	 * 
	 * @param addressTypeCode
	 * @see AddressType
	 */
	void setAddressTypeCode(String addressTypeCode);

	/**
	 * is enabled
	 * 
	 * @return
	 */
	Boolean isEnabled();

	/**
	 * set enabled
	 * 
	 * @param enabled
	 */
	void setEnabled(Boolean enabled);
}
