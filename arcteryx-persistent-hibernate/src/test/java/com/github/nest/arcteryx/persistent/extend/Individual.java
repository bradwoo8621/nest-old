/**
 * 
 */
package com.github.nest.arcteryx.persistent.extend;

import org.apache.commons.lang3.StringUtils;

/**
 * @author brad.wu
 */
public class Individual extends Party {
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.extend.Party#getName()
	 */
	@Override
	public String getName() {
		StringBuilder name = new StringBuilder();
		name.append(this.getFirstName()).append(' ')
				.append(StringUtils.isBlank(this.getMiddleName()) ? "" : this.getMiddleName()).append(' ')
				.append(this.getLastName());
		return name.toString();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.extend.Party#getType()
	 */
	@Override
	public String getType() {
		return "I";
	}
}
