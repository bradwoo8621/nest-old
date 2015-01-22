/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.extend;

import javax.validation.constraints.NotNull;


/**
 * @author brad.wu
 *
 */
public class Person {
	private String name = null;

	/**
	 * @return the name
	 */
	@NotNull
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
