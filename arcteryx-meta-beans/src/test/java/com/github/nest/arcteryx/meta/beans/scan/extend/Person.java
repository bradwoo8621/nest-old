/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.scan.extend;

import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBean;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanProperty;
import com.github.nest.arcteryx.meta.beans.annotation.TheNumber;

/**
 * @author brad.wu
 */
@ArcteryxBean(name = "Person", description = "A person")
public class Person {
	@ArcteryxBeanProperty(description = "Age of Person")
	@TheNumber(min = 1)
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
