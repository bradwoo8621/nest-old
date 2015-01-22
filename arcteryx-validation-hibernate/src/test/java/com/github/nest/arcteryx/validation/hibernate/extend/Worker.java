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
	private int age = 0;

	/**
	 * @return the age
	 */
	@Range(min = 10)
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.hibernate.extend.Person#getName()
	 */
	@Override
	public String getName() {
		return super.getName();
	}
}
