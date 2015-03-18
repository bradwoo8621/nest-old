/**
 * 
 */
package com.github.nest.goose.internal.location;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.goose.location.IProvince;

/**
 * province
 * 
 * @author brad.wu
 */
public class Province extends AbstractCodeBaseBean implements IProvince {
	private static final long serialVersionUID = -6416038339763360750L;

	private String code = null;
	private String name = null;
	private String countryCode = null;

	public Province() {
	}

	public Province(String code, String name, String countryCode) {
		this.setCode(code);
		this.setName(name);
		this.setCountryCode(countryCode);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.ICodeBaseBean#getCode()
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
	 * @see com.github.nest.goose.location.IProvince#getName()
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
	 * @see com.github.nest.goose.location.IProvince#getCountryCode()
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
