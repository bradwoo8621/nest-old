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
	public void testSingletonRepository() {
		IResourceDescriptorContext repository = context.getBean("descriptor.context", IResourceDescriptorContext.class);
		IResourceDescriptorContext repository1 = context
				.getBean("descriptor.context", IResourceDescriptorContext.class);
		assertEquals(repository, repository1);
	}

	@Test
	public void testWorker() {
		IResourceDescriptorContext repository = context.getBean("descriptor.context", IResourceDescriptorContext.class);
		IResourceDescriptor descriptor = repository.get(Worker.class);
		assertEquals("Person", descriptor.getName());
		assertEquals("A person", descriptor.getDescription());

		Collection<IPropertyDescriptor> properties = descriptor.getProperties(false);
		assertEquals(1, properties.size());
		for (IPropertyDescriptor property : properties) {
			if ("name".equals(property.getName())) {
				assertEquals("Name of person", property.getDescription());
			}
		}

		IResourceDescriptor descriptor1 = repository.get(new Worker());
		assertEquals(descriptor, descriptor1);
	}

	@Test
	public void testStudent() {
		IResourceDescriptorContext repository = context.getBean("descriptor.context", IResourceDescriptorContext.class);
		IResourceDescriptor descriptor = repository.get(IStudent.class);
		assertEquals("Student", descriptor.getName());
		assertEquals("A student", descriptor.getDescription());

		Collection<IPropertyDescriptor> properties = descriptor.getProperties(false);
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
		IResourceDescriptorContext repository = context.getBean("descriptor.context", IResourceDescriptorContext.class);
		IResourceDescriptor descriptor = repository.get(IPerson.class);
		assertEquals("Person", descriptor.getName());
		assertEquals("A person", descriptor.getDescription());

		Collection<IPropertyDescriptor> properties = descriptor.getProperties(false);
		assertEquals(1, properties.size());
		for (IPropertyDescriptor property : properties) {
			if ("name".equals(property.getName())) {
				assertEquals("Name of person", property.getDescription());
			}
		}
	}
}
