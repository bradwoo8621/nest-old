/**
 * 
 */
package com.github.nest.arcteryx.validation.oval;

import com.github.nest.arcteryx.validation.oval.constraint.UpperCase;

/**
 * @author brad.wu
 *
 */
public class UpperCaseObject {
	@UpperCase
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
