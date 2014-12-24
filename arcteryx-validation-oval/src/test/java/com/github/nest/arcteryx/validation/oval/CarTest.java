/**
 * 
 */
package com.github.nest.arcteryx.validation.oval;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.configuration.annotation.BeanValidationAnnotationsConfigurer;

import org.junit.Test;

import com.github.nest.arcteryx.validation.oval.OvalValidator;

public class CarTest {

	@Test
	public void licensePlateTooShort() {
		OvalValidator validator = new OvalValidator(new AnnotationsConfigurer(), new BeanValidationAnnotationsConfigurer());

		Car car = new Car(null, "D", 1);

		List<ConstraintViolation> constraintViolations = validator.validate(car);

		assertEquals(3, constraintViolations.size());
//		assertEquals("size must be between 2 and 14", constraintViolations.iterator().next().getMessage());

		long time = System.currentTimeMillis();
		for (int index = 0, count = 10000; index < count; index++) {
			validator.validate(car);
		}
		long spent = System.currentTimeMillis() - time;
		System.out.println(spent);
		System.out.println(((double) spent) / 10000);
	}
}
