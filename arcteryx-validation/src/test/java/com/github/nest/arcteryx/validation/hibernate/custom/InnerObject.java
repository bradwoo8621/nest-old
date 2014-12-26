package com.github.nest.arcteryx.validation.hibernate.custom;

public class InnerObject {
	private String string = null;

	public InnerObject(String string) {
		this.string = string;
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @param string
	 *            the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}
}