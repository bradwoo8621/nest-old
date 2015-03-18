/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;

import com.github.nest.goose.location.ICity;
import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.location.IDistrict;
import com.github.nest.goose.location.IProvince;

/**
 * party address interface
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
	 */
	void setAddressId(Long addressId);

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
	 * get district
	 * 
	 * @return
	 */
	IDistrict getDistrict();

	/**
	 * set district
	 * 
	 * @param district
	 */
	void setDistrict(IDistrict district);

	/**
	 * get city
	 * 
	 * @return
	 */
	ICity getCity();

	/**
	 * set city
	 * 
	 * @param city
	 */
	void setCity(ICity city);

	/**
	 * get province
	 * 
	 * @return
	 */
	IProvince getProvince();

	/**
	 * set province
	 * 
	 * @param province
	 */
	void setProvince(IProvince province);

	/**
	 * get country
	 * 
	 * @return
	 */
	ICountry getCountry();

	/**
	 * set country
	 * 
	 * @param country
	 */
	void setCountry(ICountry country);

	/**
	 * get address line 1
	 * 
	 * @return
	 */
	String getAddressLine1();

	/**
	 * set addressline1
	 * 
	 * @param addressLine1
	 */
	void setAddressLine1(String addressLine1);

	/**
	 * get address line 2
	 * 
	 * @return
	 */
	String getAddressLine2();

	/**
	 * set addressline2
	 * 
	 * @param addressLine2
	 */
	void setAddressLine2(String addressLine2);

	/**
	 * get address line 3
	 * 
	 * @return
	 */
	String getAddressLine3();

	/**
	 * set addressline3
	 * 
	 * @param addressLine3
	 */
	void setAddressLine3(String addressLine3);

	/**
	 * get address line 4
	 * 
	 * @return
	 */
	String getAddressLine4();

	/**
	 * set addressline4
	 * 
	 * @param addressLine4
	 */
	void setAddressLine4(String addressLine4);

	/**
	 * get address line 5
	 * 
	 * @return
	 */
	String getAddressLine5();

	/**
	 * set addressline5
	 * 
	 * @param addressLine5
	 */
	void setAddressLine5(String addressLine5);

	/**
	 * get telephone
	 * 
	 * @return
	 */
	String getTelephone();

	/**
	 * set telephone
	 * 
	 * @param telephone
	 */
	void setTelephone(String telephone);

	/**
	 * get fax
	 * 
	 * @return
	 */
	String getFax();

	/**
	 * set fax
	 * 
	 * @param fax
	 */
	void setFax(String fax);

	/**
	 * is enabled or not
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
