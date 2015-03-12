/**
 * 
 */
package com.github.nest.goose.internal.human;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.human.IGender;

/**
 * gender
 * 
 * @author brad.wu
 */
public class Gender implements IGender {
	private static final long serialVersionUID = 8457821832961288245L;

	public static final IGender MALE = new Gender("M", "Male");
	public static final IGender FEMALE = new Gender("F", "Female");

	private String code = null;
	private String name = null;

	public Gender() {
	}

	public Gender(String code, String name) {
		this.setCode(code);
		this.setName(name);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.human.IGender#getCode()
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
	 * @see com.github.nest.goose.human.IGender#getName()
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
