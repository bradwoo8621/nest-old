/**
 * 
 */
package com.github.nest.arcteryx.meta;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.context.Context;

/**
 * @author brad.wu
 */
@FixMethodOrder
public class TestContext {
	private static ApplicationContext context = null;

	@BeforeClass
	public static void test() {
		context = Context.createApplicationContextByClassPath("context", "/context.xml");
		System.out.println("Context ID: [" + context.getId() + "].");
	}

	@Test
	public void testSingletonRegistry() {
		IResourceDescriptorContext context1 = context.getBean("descriptor.context", IResourceDescriptorContext.class);
		IResourceDescriptorContext context2 = context.getBean("descriptor.context", IResourceDescriptorContext.class);
		assertEquals(context1, context2);
	}

	@Test
	public void testWorker() {
		IResourceDescriptorContext resourceContext = context.getBean("descriptor.context",
				IResourceDescriptorContext.class);
		IResourceDescriptor descriptor = resourceContext.getRecursive(Worker.class).get(0);
		assertEquals("Person", descriptor.getName());
		assertEquals("A person", descriptor.getDescription());

		Collection<IPropertyDescriptor> properties = descriptor.getProperties();
		assertEquals(1, properties.size());
		for (IPropertyDescriptor property : properties) {
			if ("name".equals(property.getName())) {
				assertEquals("Name of person", property.getDescription());
			}
		}

		IResourceDescriptor descriptor1 = resourceContext.getRecursive(new Worker()).get(0);
		assertEquals(descriptor, descriptor1);
	}

	@Test
	public void testStudent() {
		IResourceDescriptorContext resourceContext = context.getBean("descriptor.context",
				IResourceDescriptorContext.class);
		IResourceDescriptor descriptor = resourceContext.get(IStudent.class);
		assertEquals("Student", descriptor.getName());
		assertEquals("A student", descriptor.getDescription());

		Collection<IPropertyDescriptor> properties = descriptor.getDeclaredProperties();
		assertEquals(2, properties.size());
		for (IPropertyDescriptor property : properties) {
			if ("school".equals(property.getName())) {
				assertEquals("School of student", property.getDescription());
			} else if ("grade".equals(property.getName())) {
				assertEquals("Grade of student", property.getDescription());
			}
		}

		properties = descriptor.getProperties();
		assertEquals(3, properties.size());
		for (IPropertyDescriptor property : properties) {
			if ("school".equals(property.getName())) {
				assertEquals("School of student", property.getDescription());
			} else if ("name".equals(property.getName())) {
				assertEquals("Name of person", property.getDescription());
			} else if ("grade".equals(property.getName())) {
				assertEquals("Grade of student", property.getDescription());
			}
		}
	}

	@Test
	public void testPerson() {
		IResourceDescriptorContext resourceContext = context.getBean("descriptor.context",
				IResourceDescriptorContext.class);
		IResourceDescriptor descriptor = resourceContext.get(IPerson.class);
		assertEquals("Person", descriptor.getName());
		assertEquals("A person", descriptor.getDescription());

		Collection<IPropertyDescriptor> properties = descriptor.getProperties();
		assertEquals(1, properties.size());
		for (IPropertyDescriptor property : properties) {
			if ("name".equals(property.getName())) {
				assertEquals("Name of person", property.getDescription());
			}
		}
	}
}
