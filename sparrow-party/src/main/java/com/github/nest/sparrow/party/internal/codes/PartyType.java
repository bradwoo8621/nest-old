/**
 * 
 */
package com.github.nest.sparrow.party.internal.codes;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.goose.internal.AbstractCodeBaseBean;
import com.github.nest.sparrow.party.codes.IPartyType;

/**
 * party type
 * 
 * @author brad.wu
 */
public final class PartyType extends AbstractCodeBaseBean implements IPartyType {
	private static final long serialVersionUID = -5672491354635407657L;

	public static final IPartyType INDIVIDUAL = new PartyType(IPartyType.INDIVIDUAL, "Individual");
	public static final IPartyType ORGANIZATION = new PartyType(IPartyType.ORGANIZATION, "Organization");

	private String code = null;
	private String name = null;

	public PartyType() {
	}

	public PartyType(String code, String name) {
		this.setCode(code);
		this.setName(name);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.codes.IPartyType#getCode()
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
	 * @see com.github.nest.sparrow.party.codes.IPartyType#getName()
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
