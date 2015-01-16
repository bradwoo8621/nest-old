/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.extend;

import net.sf.oval.constraint.Range;

/**
 * @author brad.wu
 *
 */
public class Worker extends Person {
	@Range(min = 10)
	private int age = 0;

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
