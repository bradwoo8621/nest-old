/**
 * 
 */
package com.github.nest.arcteryx.persistent.extend;

/**
 * @author brad.wu
 */
public class Organization extends Party {
	private String abbr = null;

	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}

	/**
	 * @param abbr
	 *            the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	/**
	 * (non-Javadoc)
	 * @see com.github.nest.arcteryx.persistent.extend.Party#getType()
	 */
	@Override
	public String getType() {
		return "O";
	}
}
