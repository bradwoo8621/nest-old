/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.validation.hibernate.constraints.Length;

/**
 * @author brad.wu
 *
 */
public class TestLength {
	private static Validator validator;

	@Length(min = 2, max = 10)
	private String string = null;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * @return the string
	 */
	protected String getString() {
		return string;
	}

	/**
	 * @param string
	 *            the string to set
	 */
	protected void setString(String string) {
		this.string = string;
	}

	@Test
	public void testLength() {
		this.setString("a");
		Set<ConstraintViolation<TestLength>> constraintViolations = validator.validate(this);

		assertNotNull(constraintViolations);
		assertEquals(1, constraintViolations.size());
	}
}
