/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.scan;

import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanNameGenerator;

/**
 * @author brad.wu
 */
public class BeanScanTest {
	@BeforeClass
	public static void initialize() {
		Context.createApplicationContextByClassPath(BeanScanTest.class.getName(), "/scan/Scan.xml");
	}

	@Test
	public void test() {
		ApplicationContext context = Context.getContext(BeanScanTest.class.getName());

		Person person = context.getBean(ArcteryxBeanNameGenerator.PREFIX + Person.class.getName(), Person.class);
		person.setName("abc");
		Person person1 = context.getBean("person", Person.class);
		assertNotEquals(person, person1);
	}
}
