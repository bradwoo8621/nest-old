/**
 * 
 */
package com.github.nest.sparrow.party.internal.codes;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.sparrow.party.codes.IJobTitle;

/**
 * job title
 * 
 * @author brad.wu
 */
public class JobTitle extends AbstractCodeBaseBean implements IJobTitle {
	private static final long serialVersionUID = 8368517668653592172L;

	private String code = null;
	private String name = null;

	public JobTitle() {
	}

	public JobTitle(String code, String name) {
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
	 * @see com.github.nest.sparrow.party.codes.IJobTitle#getName()
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
