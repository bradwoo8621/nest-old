/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.extend;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

/**
 * @author brad.wu
 *
 */
public class TestExtend {
	@Test
	public void test() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Person person = new Person();
		Set<ConstraintViolation<Person>> personViolations = validator.validate(person);
		assertEquals(1, personViolations.size());

		Worker worker = new Worker();
		Set<ConstraintViolation<Worker>> workerViolations = validator.validate(worker);
		assertEquals(2, workerViolations.size());

		Student student = new Student();
		Set<ConstraintViolation<Student>> studentViolations = validator.validate(student);
		assertEquals(1, studentViolations.size());
	}
}
