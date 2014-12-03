/**
 * 
 */
package com.github.nest.arcteryx.validation.oval;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author brad.wu
 */
public class MaxLengthTest {

	@Test
	public void test() {
		MaxLengthObject bo = new MaxLengthObject();
		bo.setName("abcde");

		Validator validator = new Validator();
		// collect the constraint violations
		List<ConstraintViolation> violations = validator.validate(bo);

		Assert.assertEquals(1, violations.size());
	}
}
