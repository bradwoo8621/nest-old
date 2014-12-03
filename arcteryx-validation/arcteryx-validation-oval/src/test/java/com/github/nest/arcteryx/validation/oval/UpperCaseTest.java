/**
 * 
 */
package com.github.nest.arcteryx.validation.oval;

import java.util.List;

import net.sf.oval.ConstraintViolation;

import org.junit.Assert;
import org.junit.Test;

import com.github.nest.arcteryx.validation.oval.OvalValidator;

/**
 * @author brad.wu
 *
 */
public class UpperCaseTest {
	@Test
	public void test() {
		UpperCaseObject bo = new UpperCaseObject();
		bo.setName("UPPEr");

		OvalValidator validator = new OvalValidator();
		// collect the constraint violations
		List<ConstraintViolation> violations = validator.validate(bo);

		Assert.assertEquals(1, violations.size());
//
//		long time = System.currentTimeMillis();
//		for (int index = 0, count = 10000; index < count; index++) {
//			validator.validate(bo);
//		}
//		long spent = System.currentTimeMillis() - time;
//		System.out.println(spent);
//		System.out.println(((double) spent) / 10000);
	}
}
