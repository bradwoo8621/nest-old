/**
 * 
 */
package com.github.nest.goose.internal.location;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.goose.location.ICountry;

/**
 * country implementation
 * 
 * @author brad.wu
 */
public class Country extends AbstractCodeBaseBean implements ICountry {
	private static final long serialVersionUID = 8072862832627880776L;

	private String name = null;
	private String abbr3 = null;
	private String abbr2 = null;
	private String internationalDialingCode = null;

	public Country() {
	}

	public Country(String name, String abbr3, String abbr2, String internationalDialingCode) {
		this.setName(name);
		this.setAbbr3(abbr3);
		this.setAbbr2(abbr2);
		this.setInternationalDialingCode(internationalDialingCode);
	}

	/**
	 * same as {@linkplain #getAbbr3()}
	 * 
	 * @see com.github.nest.goose.location.ICountry#getCode()
	 */
	@Override
	public String getCode() {
		return this.getAbbr3();
	}

	/**
	 * set code, same as {@linkplain #setAbbr3(String)}
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.setAbbr3(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICountry#getName()
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
	 * @see com.github.nest.goose.location.ICountry#getAbbr3()
	 */
	@Override
	public String getAbbr3() {
		return this.abbr3;
	}

	/**
	 * @param abbr3
	 *            the abbr3 to set
	 */
	public void setAbbr3(String abbr3) {
		assert StringUtils.isNotBlank(name) : "3-letter abbreviation cannot be null or blank string.";

		this.abbr3 = abbr3;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICountry#getAbbr2()
	 */
	@Override
	public String getAbbr2() {
		return this.abbr2;
	}

	/**
	 * @param abbr2
	 *            the abbr2 to set
	 */
	public void setAbbr2(String abbr2) {
		assert StringUtils.isNotBlank(name) : "2-letter abbreviation cannot be null or blank string.";

		this.abbr2 = abbr2;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.location.ICountry#getInternationalDialingCode()
	 */
	@Override
	public String getInternationalDialingCode() {
		return this.internationalDialingCode;
	}

	/**
	 * @param internationalDialingCode
	 *            the internationalDialingCode to set
	 */
	public void setInternationalDialingCode(String internationalDialingCode) {
		assert StringUtils.isNotBlank(name) : "Internaional dialing code cannot be null or blank string.";

		this.internationalDialingCode = internationalDialingCode;
	}
}
