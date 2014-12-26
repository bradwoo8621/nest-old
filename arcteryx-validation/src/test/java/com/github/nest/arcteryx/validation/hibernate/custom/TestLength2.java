/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.custom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.ElementType;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.nest.arcteryx.validation.hibernate.defs.LengthDef;

/**
 * @author brad.wu
 *
 */
public class TestLength2 {
	private static Validator validator;

	private String string = null;
	private TestObject object = new TestObject("b");

	@BeforeClass 
	public static void setUp() {
		HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class).configure();

		ConstraintMapping constraintMapping = configuration.createConstraintMapping();
		constraintMapping.type(TestLength2.class).property("string", ElementType.FIELD)
				.constraint(new LengthDef().min(2).max(10)).property("object", ElementType.FIELD)
				.constraint(new LengthDef().min(2).max(10).target("string"))
				.constraint(new LengthDef().min(2).max(10).target("jxpath:inners[1]/string"));

		validator = configuration.addMapping(constraintMapping).buildValidatorFactory().getValidator();
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @param string
	 *            the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}

	/**
	 * @return the object
	 */
	public TestObject getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(TestObject object) {
		this.object = object;
	}

	@Test
	public void testLength() {
		this.setString("a");

		Set<ConstraintViolation<TestLength2>> constraintViolations = validator.validate(this);

		assertNotNull(constraintViolations);
		assertEquals(3, constraintViolations.size());
	}
}
