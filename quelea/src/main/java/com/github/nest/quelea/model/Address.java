/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.github.nest.quelea.codes.AddressType;
import com.github.nest.quelea.codes.City;
import com.github.nest.quelea.codes.Country;
import com.github.nest.quelea.codes.District;
import com.github.nest.quelea.codes.Province;

/**
 * party address
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_PARTY_ADDRESS")
@SequenceGenerator(name = "S_PARTY_ADDRESS", sequenceName = "S_PARTY_ADDRESS")
public class Address implements Serializable {
	private static final long serialVersionUID = -2101841312931153607L;

	@Id
	@Column(name = "ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARTY_ADDRESS")
	private Long addressId = null;

	@Column(name = "ADDRESS_TYPE_CODE")
	private String addressTypeCode = null;

	@Column(name = "CITY_CODE")
	private String cityCode = null;

	@Column(name = "COUNTRY_CODE")
	private String countryCode = null;

	@Column(name = "DISTRICT_CODE")
	private String districtCode = null;

	@Column(name = "LINE1")
	private String line1 = null;

	@Column(name = "LINE2")
	private String line2 = null;

	@Column(name = "LINE3")
	private String line3 = null;

	@Column(name = "LINE4")
	private String line4 = null;

	@Column(name = "LINE5")
	private String line5 = null;

	@Column(name = "POSTCODE")
	private String postcode = null;

	@Column(name = "PROVINCE_CODE")
	private String provinceCode = null;

	@Column(name = "IS_ENABLED")
	private Boolean enabled = null;

	@ManyToOne
	@JoinColumn(name = "PARTY_ID", nullable = false)
	private Party party = null;

	/**
	 * @return the addressId
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the addressTypeCode
	 * @see AddressType
	 */
	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	/**
	 * @param addressTypeCode
	 *            the addressTypeCode to set
	 * @see AddressType
	 */
	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	/**
	 * @return the cityCode
	 * @see City
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 * @see City
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the countryCode
	 * @see Country
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 * @see Country
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the districtCode
	 * @see District
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode
	 *            the districtCode to set
	 * @see District
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the line1
	 */
	public String getLine1() {
		return line1;
	}

	/**
	 * @param line1
	 *            the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * @return the line2
	 */
	public String getLine2() {
		return line2;
	}

	/**
	 * @param line2
	 *            the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * @return the line3
	 */
	public String getLine3() {
		return line3;
	}

	/**
	 * @param line3
	 *            the line3 to set
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	/**
	 * @return the line4
	 */
	public String getLine4() {
		return line4;
	}

	/**
	 * @param line4
	 *            the line4 to set
	 */
	public void setLine4(String line4) {
		this.line4 = line4;
	}

	/**
	 * @return the line5
	 */
	public String getLine5() {
		return line5;
	}

	/**
	 * @param line5
	 *            the line5 to set
	 */
	public void setLine5(String line5) {
		this.line5 = line5;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the provinceCode
	 * @see Province
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 * @see Province
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * @param party
	 *            the party to set
	 */
	public void setParty(Party party) {
		this.party = party;
	}
}
