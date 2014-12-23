/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.io.Serializable;

/**
 * @author brad.wu
 *
 */
public class PreloadedBean implements Serializable {
	private static final long serialVersionUID = 6594526495712117986L;

	private String id = null;
	private String name = null;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
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
}
