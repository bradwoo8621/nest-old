/**
 * 
 */
package com.github.nest.arcteryx.persistent.first;

import java.sql.Timestamp;

/**
 * @author brad.wu
 *
 */
public class Person {
	private Long id = null;
	private String name = null;
	private Integer version = null;
	private Timestamp vts = null;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the vts
	 */
	public Timestamp getVts() {
		return vts;
	}

	/**
	 * @param vts
	 *            the vts to set
	 */
	public void setVts(Timestamp vts) {
		this.vts = vts;
	}
}