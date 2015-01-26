/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.scanextend;

import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBean;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanProperty;
import com.github.nest.arcteryx.meta.beans.annotation.BeanPropertyConstraintReorganizer;
import com.github.nest.arcteryx.meta.beans.annotation.TheNumber;

/**
 * @author brad.wu
 *
 */
@ArcteryxBean(name = "Worker", description = "A worker", parent = Person.class)
public class Worker extends Person {
	/**
	 * @return the age
	 */
	@ArcteryxBeanProperty(description = "Age of Worker")
	@TheNumber(min = 20)
	@BeanPropertyConstraintReorganizer
	public int getAge() {
		return super.getAge();
	}
}
