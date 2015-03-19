/**
 * 
 */
package com.github.nest.sparrow.party.internal.codes;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.sparrow.party.codes.IOccupation;

/**
 * Occupation
 * 
 * @author brad.wu
 */
public class Occupation extends AbstractCodeBaseBean implements IOccupation {
	private static final long serialVersionUID = 6675457458267634142L;

	private String code = null;
	private String name = null;

	public Occupation() {
	}

	public Occupation(String code, String name) {
		this.setCode(code);
		this.setName(name);
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
		assert StringUtils.isNotBlank(code) : "Code cannot be null or empty string.";
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.codes.IOccupation#getName()
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
		assert StringUtils.isNotBlank(name) : "Name cannot be null or empty string.";
		this.name = name;
	}
}
