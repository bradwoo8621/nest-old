/**
 * 
 */
package com.github.nest.arcteryx.persistent.embed;

/**
 * @author brad.wu
 */
public class Country {
	private String name = null;
	private String abbr = null;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the abbr
	 */
	public String getAbbr() {
		return abbr;
	}
	/**
	 * @param abbr the abbr to set
	 */
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
}
