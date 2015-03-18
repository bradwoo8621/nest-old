/**
 * 
 */
package com.github.nest.goose.internal.location;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.goose.location.IDistrict;

/**
 * district
 * 
 * @author brad.wu
 */
public class District extends AbstractCodeBaseBean implements IDistrict {
	private static final long serialVersionUID = 8577785533644661210L;

	private String code = null;
	private String name = null;
	private String cityCode = null;
	private String provinceCode = null;
	private String countryCode = null;

	public District() {
	}

	public District(String code, String name, String cityCode, String provinceCode, String countryCode) {
		this.setCode(code);
		this.setName(name);
		this.setCityCode(cityCode);
		this.setProvinceCode(provinceCode);
		this.setCountryCode(countryCode);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.IDistrict#getCode()
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
	 * @see com.github.nest.goose.location.IDistrict#getName()
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
	 * @see com.github.nest.goose.location.IDistrict#getCityCode()
	 */
	@Override
	public String getCityCode() {
		return this.cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		assert StringUtils.isNotBlank(cityCode) : "City code cannot be null or blank string.";

		this.cityCode = cityCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.IDistrict#getProvinceCode()
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
	 * @see com.github.nest.goose.location.IDistrict#getCountryCode()
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
		assert StringUtils.isNotBlank(countryCode) : "Country code cannot be null or blank string.";

		this.countryCode = countryCode;
	}
}
