/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.extend;

import org.hibernate.validator.constraints.Range;


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
