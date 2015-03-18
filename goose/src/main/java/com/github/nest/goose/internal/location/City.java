/**
 * 
 */
package com.github.nest.goose.internal.location;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.goose.location.ICity;

/**
 * city
 * 
 * @author brad.wu
 */
public class City extends AbstractCodeBaseBean implements ICity {
	private static final long serialVersionUID = 2629914376047679688L;

	private String code = null;
	private String name = null;
	private String provinceCode = null;
	private String countryCode = null;

	public City() {
	}

	public City(String code, String name, String provinceCode, String countryCode) {
		this.setCode(code);
		this.setName(name);
		this.setProvinceCode(provinceCode);
		this.setCountryCode(countryCode);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICity#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		assert StringUtils.isNotBlank(code) : "Code cannot be null or blank string.";

		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICity#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		assert StringUtils.isNotBlank(name) : "Name cannot be null or blank string.";

		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICity#getProvinceCode()
	 */
	@Override
	public String getProvinceCode() {
		return this.provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		assert StringUtils.isNotBlank(provinceCode) : "Province code cannot be null or blank string.";

		this.provinceCode = provinceCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICity#getCountryCode()
	 */
	@Override
	public String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		assert StringUtils.isNotBlank(countryCode) : "CountryCode cannot be null or blank string.";

		this.countryCode = countryCode;
	}
}
