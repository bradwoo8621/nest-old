/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.internal.constraints.NotNull;

/**
 * @author brad.wu
 */
public class ValidationTest {
	@BeforeClass
	public static void initialize() {
		Context.createApplicationContextByClassPath("Beans", "/validation/ValidationTest.xml");
	}

	@Test
	public void test() {
		IBeanDescriptorContext context = Context.getContext("Beans").getBean("beans.context",
				BeanDescriptorContext.class);
		BeanDescriptor descriptor = context.get(Person.class);
		assertEquals("Person", descriptor.getName());
		assertEquals("A person", descriptor.getDescription());

		Collection<IBeanPropertyDescriptor> properties = descriptor.getDeclaredBeanProperties();
		assertEquals(1, properties.size());

		IBeanPropertyDescriptor property = descriptor.getProperty("name");
		assertNotNull(property);
		assertEquals("Name of person", property.getDescription());

		assertNotNull(property.getConstraint());
		assertEquals(NotNull.class, property.getConstraint().getClass());

		Person person = new Person();
		Object o = descriptor.getValidator().validate(person);
		assertNotNull(o);
	}
}
