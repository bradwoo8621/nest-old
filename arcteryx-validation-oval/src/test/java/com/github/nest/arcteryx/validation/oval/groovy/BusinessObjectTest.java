/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.groovy;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author brad.wu
 */
public class BusinessObjectTest {
	@Test
	public void test() {
		BusinessObject bo = new BusinessObject();
		bo.deliveryAddress = "abc";
		bo.invoiceAddress = "def";
		bo.mailingAddress = "xyz";

		Validator validator = new Validator();
		// collect the constraint violations
		List<ConstraintViolation> violations = validator.validate(bo);

		Assert.assertEquals(1, violations.size());
	}
}
