/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.extend;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.junit.Test;

/**
 * @author brad.wu
 *
 */
public class TestExtend {
	@Test
	public void test() {
		Validator validator = new Validator();
		Person person = new Person();
		List<ConstraintViolation> violations = validator.validate(person);
		assertEquals(1, violations.size());
		
		Worker worker =new Worker();
		violations = validator.validate(worker);
		assertEquals(2, violations.size());
		
		Student student = new Student();
		violations = validator.validate(student);
		assertEquals(1, violations.size());
	}
}
