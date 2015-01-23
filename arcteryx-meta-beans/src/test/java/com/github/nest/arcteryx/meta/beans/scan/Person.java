/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.scan;

import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBean;

/**
 * @author brad.wu
 *
 */
@ArcteryxBean(name = "Person")
public class Person {
	private String name = null;

	public Person() {
		super();
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
