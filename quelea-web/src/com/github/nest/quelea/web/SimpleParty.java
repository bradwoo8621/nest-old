/**
 * 
 */
package com.github.nest.quelea.web;

/**
 * @author brad.wu
 *
 */
public class SimpleParty {
	private Long partyId = null;
	private String partyName = null;
	private String partyTypeCode = null;

	/**
	 * @return the partyId
	 */
	public Long getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId
	 *            the partyId to set
	 */
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName
	 *            the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the partyTypeCode
	 */
	public String getPartyTypeCode() {
		return partyTypeCode;
	}

	/**
	 * @param partyTypeCode
	 *            the partyTypeCode to set
	 */
	public void setPartyTypeCode(String partyTypeCode) {
		this.partyTypeCode = partyTypeCode;
	}
}
