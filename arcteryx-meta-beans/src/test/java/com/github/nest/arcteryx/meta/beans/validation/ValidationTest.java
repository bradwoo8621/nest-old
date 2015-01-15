/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.AssertValid;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;
import com.github.nest.arcteryx.meta.beans.IBeanValidator;
import com.github.nest.arcteryx.meta.beans.IConstraintViolation;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.internal.constraints.PropertyConstraints;

/**
 * @author brad.wu
 */
public class ValidationTest {
	@BeforeClass
	public static void initialize() {
		Context.createApplicationContextByClassPath(ValidationTest.class.getName(), "/validation/ValidationTest.xml");
	}

	@Test
	public void testDefinition() {
		IBeanDescriptorContext context = Context.getContext(ValidationTest.class.getName()).getBean("beans.context",
				BeanDescriptorContext.class);
		BeanDescriptor descriptor = context.get(Person.class);
		assertEquals("Person", descriptor.getName());
		assertEquals("A person", descriptor.getDescription());

		Collection<IBeanPropertyDescriptor> properties = descriptor.getDeclaredBeanProperties();
		assertEquals(6, properties.size());

		IBeanPropertyDescriptor property = descriptor.getProperty("name");
		assertNotNull(property);
		assertEquals("Name of person", property.getDescription());

		assertNotNull(property.getConstraint());
		assertEquals(PropertyConstraints.class, property.getConstraint().getClass());
		IBeanPropertyConstraint constraint = property.getConstraint();
		List<IBeanPropertyConstraint> constraints = constraint.getConstraintsRecursive();
		assertEquals(5, constraints.size());
	}

	@Test
	public void testName() {
		IBeanDescriptorContext context = Context.getContext(ValidationTest.class.getName()).getBean("beans.context",
				BeanDescriptorContext.class);
		BeanDescriptor descriptor = context.get(Person.class);
		IBeanValidator validator = descriptor.getValidator();

		Person person = new Person();
		List<IConstraintViolation> results = validator.validate(person, "notnull");
		assertNotNull(results);

		person.setName("");
		results = validator.validate(person, "notempty");
		assertNotNull(results);

		person.setName(" ");
		results = validator.validate(person, "notblank");
		assertNotNull(results);

		person.setName("1234567890123456789012345678901");
		results = validator.validate(person, "length");
		assertNotNull(results);

		results = validator.validate(person, "textformat");
		assertNotNull(results);
	}

	@Test
	public void testFather() {
		IBeanDescriptorContext context = Context.getContext(ValidationTest.class.getName()).getBean("beans.context",
				BeanDescriptorContext.class);
		BeanDescriptor descriptor = context.get(Person.class);
		IBeanValidator validator = descriptor.getValidator();

		Person person = new Person();
		Person father = new Person();
		father.setAge(10);
		person.setFather(father);
		List<IConstraintViolation> results = validator.validate(person);
		assertNotNull(results);

		results = validator.validate(person, "target");
		assertNotNull(results);
		assertEquals(1, results.size());

		IConstraintViolation violation = results.get(0);
		assertEquals("father.age", violation.getRelativePath());
	}

	@Test
	public void testChildren() {
		IBeanDescriptorContext context = Context.getContext(ValidationTest.class.getName()).getBean("beans.context",
				BeanDescriptorContext.class);
		BeanDescriptor descriptor = context.get(Person.class);
		IBeanValidator validator = descriptor.getValidator();

		Person person = new Person();
		List<Person> children = new ArrayList<Person>();
		Person child = new Person();
		child.setName("APerson");
		child.setAge(10);
		children.add(child);
		child = new Person();
		children.add(child);
		person.setChildren(children);
		List<IConstraintViolation> results = validator.validate(person);
		assertNotNull(results);
		// name not null
		// age in 1..200
		// age > 0 in class level
		// children[1] assert
		assertEquals(4, results.size());
		for (IConstraintViolation violation : results) {
			String errorCode = violation.getErrorCode();
			if (com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints.Number.class.getName().equals(
					errorCode)) {
				assertEquals("age", violation.getRelativePath());
			} else if (NotNull.class.getName().equals(errorCode)) {
				assertEquals("name", violation.getRelativePath());
			} else if (Assert.class.getName().equals(errorCode)) {
				assertEquals("self", violation.getRelativePath());
			} else if (AssertValid.class.getName().equals(errorCode)) {
				assertEquals("children[1]", violation.getRelativePath());
				// name not null
				// age in 1..200
				// age > 0 in class level
				List<IConstraintViolation> sub = violation.getConstraintCauses();
				assertEquals(3, sub.size());
				for (IConstraintViolation subV : sub) {
					errorCode = subV.getErrorCode();
					if (com.github.nest.arcteryx.meta.beans.internal.validators.oval.constraints.Number.class.getName()
							.equals(errorCode)) {
						assertEquals("age", subV.getRelativePath());
					} else if (NotNull.class.getName().equals(errorCode)) {
						assertEquals("name", subV.getRelativePath());
					} else if (Assert.class.getName().equals(errorCode)) {
						assertEquals("self", subV.getRelativePath());
					}
				}
			}
		}
	}

	@Test
	public void testBean() {
		IBeanDescriptorContext context = Context.getContext(ValidationTest.class.getName()).getBean("beans.context",
				BeanDescriptorContext.class);
		BeanDescriptor descriptor = context.get(Person.class);
		IBeanValidator validator = descriptor.getValidator();

		Person person = new Person();
		List<IConstraintViolation> results = validator.validate(person, "beanscript");
		assertNotNull(results);
		assertEquals(1, results.size());
		assertEquals("self", results.get(0).getRelativePath());
	}
}
