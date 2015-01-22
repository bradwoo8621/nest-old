/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.extend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.meta.beans.IBeanDescriptorContext;
import com.github.nest.arcteryx.meta.beans.IBeanValidator;
import com.github.nest.arcteryx.meta.beans.IConstraintViolation;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptorContext;

/**
 * @author brad.wu
 */
public class ExtendOValValidationTest {
	@BeforeClass
	public static void initialize() {
		Context.createApplicationContextByClassPath(ExtendOValValidationTest.class.getName(),
				"/extend/OValValidationTest.xml");
	}

	@Test
	public void test() {
		IBeanDescriptorContext context = Context.getContext(ExtendOValValidationTest.class.getName()).getBean(
				"beans.context", BeanDescriptorContext.class);
		{
			BeanDescriptor descriptor = context.get(Person.class);
			IBeanValidator validator = descriptor.getValidator();

			Person person = new Person();
			List<IConstraintViolation> violations = validator.validate(person);
			assertNotNull(violations);
			assertEquals(1, violations.size());
		}

		{
			BeanDescriptor descriptor = context.get(Worker.class);
			IBeanValidator validator = descriptor.getValidator();

			List<IConstraintViolation> violations = validator.validate(new Worker());
			assertNotNull(violations);
			assertEquals(1, violations.size());
		}
	}
}
