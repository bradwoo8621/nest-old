/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.goose.location.ICity;
import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.location.IDistrict;
import com.github.nest.goose.location.IProvince;
import com.github.nest.sparrow.party.IAddress;

/**
 * address
 * 
 * @author brad.wu
 */
public class Address implements IAddress {
	private static final long serialVersionUID = -4352489899258502824L;
	private Long addressId = null;
	private String postcode = null;
	private IDistrict district = null;
	private ICity city = null;
	private IProvince province = null;
	private ICountry country = null;
	private String addressLine1 = null;
	private String addressLine2 = null;
	private String addressLine3 = null;
	private String addressLine4 = null;
	private String addressLine5 = null;
	private String telephone = null;
	private String fax = null;
	private Boolean enabled = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getAddressId()
	 */
	@Override
	public Long getAddressId() {
		return this.addressId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setAddressId(java.lang.Long)
	 */
	@Override
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getPostcode()
	 */
	@Override
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setPostcode(java.lang.String)
	 */
	@Override
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getDistrict()
	 */
	@Override
	public IDistrict getDistrict() {
		return this.district;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setDistrict(com.github.nest.goose.location.IDistrict)
	 */
	@Override
	public void setDistrict(IDistrict district) {
		this.district = district;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getCity()
	 */
	@Override
	public ICity getCity() {
		return this.city;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setCity(com.github.nest.goose.location.ICity)
	 */
	@Override
	public void setCity(ICity city) {
		this.city = city;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getProvince()
	 */
	@Override
	public IProvince getProvince() {
		return this.province;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setProvince(com.github.nest.goose.location.IProvince)
	 */
	@Override
	public void setProvince(IProvince province) {
		this.province = province;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getCountry()
	 */
	@Override
	public ICountry getCountry() {
		return this.country;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setCountry(com.github.nest.goose.location.ICountry)
	 */
	@Override
	public void setCountry(ICountry country) {
		this.country = country;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getAddressLine1()
	 */
	@Override
	public String getAddressLine1() {
		return this.addressLine1;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setAddressLine1(java.lang.String)
	 */
	@Override
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getAddressLine2()
	 */
	@Override
	public String getAddressLine2() {
		return this.addressLine2;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setAddressLine2(java.lang.String)
	 */
	@Override
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getAddressLine3()
	 */
	@Override
	public String getAddressLine3() {
		return this.addressLine3;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setAddressLine3(java.lang.String)
	 */
	@Override
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getAddressLine4()
	 */
	@Override
	public String getAddressLine4() {
		return this.addressLine4;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setAddressLine4(java.lang.String)
	 */
	@Override
	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getAddressLine5()
	 */
	@Override
	public String getAddressLine5() {
		return this.addressLine5;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setAddressLine5(java.lang.String)
	 */
	@Override
	public void setAddressLine5(String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getTelephone()
	 */
	@Override
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setTelephone(java.lang.String)
	 */
	@Override
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#getFax()
	 */
	@Override
	public String getFax() {
		return this.fax;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setFax(java.lang.String)
	 */
	@Override
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#isEnabled()
	 */
	@Override
	public Boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IAddress#setEnabled(java.lang.Boolean)
	 */
	@Override
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
