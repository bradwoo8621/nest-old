/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.performance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.hibernate.validator.constraints.URL;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

/**
 * @author brad.wu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestObject {
	private static Validator validator;
	private static ObjectWithValidation obj = new ObjectWithValidation();
	private boolean runPeformance = true;

	@BeforeClass
	public static void before() throws IOException, SAXException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		Calendar cal = Calendar.getInstance();
		cal.set(2014, 5, 15, 12, 30, 30);
		cal.set(Calendar.MILLISECOND, 0);
		obj.setDateRange(cal.getTime());

		cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -10);
		obj.setFuture(cal.getTime());

		List<Object> list = new ArrayList<Object>();
		list.add("e1");
		list.add("e1");
		list.add("e1");
		obj.setMaxSize(list);

		list = new ArrayList<Object>();
		list.add("e1");
		list.add("e2");
		obj.setMinSize(list);

		cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 1);
		obj.setPast(cal.getTime());

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("key", "value");
		obj.setSize(map);

		InnerObjectWithValidation assertValid = obj.getAssertValid();
		List<InnerObjectWithValidation> innerList = new ArrayList<InnerObjectWithValidation>();
		assertValid.setList(innerList);
		InnerObjectWithValidation obj1 = new InnerObjectWithValidation();
		innerList.add(obj1);
		InnerObjectWithValidation obj2 = new InnerObjectWithValidation();
		obj2.setMaxLength("abc");
		innerList.add(obj2);
	}

	@Test
	public void testNotNull() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, NotNull.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, NotNull.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [NotNull] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testAssertTrue() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, AssertTrue.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, AssertTrue.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [AssertTrue] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testAssertFalse() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, AssertFalse.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, AssertFalse.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [AssertFalse] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testAssertNull() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Null.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Null.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Null] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testAssertURL() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, URL.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, URL.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [AssertURL] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testDateRange() {
	}

	@Test
	public void testDigits() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Digits.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Digits.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Digits] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testEmail() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Email.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Email.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Email] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	@Deprecated
	public void testEqualToField() {
	}

	@Test
	public void testFuture() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Future.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Future.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Future] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testHasSubstring() {
	}

	@Test
	@Deprecated
	public void testInstanceOf() {
	}

	@Test
	@Deprecated
	public void testInstanceOfAny() {
	}

	@Test
	public void testLength() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Length.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Length.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Length] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testMatchPattern() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Pattern.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Pattern.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [MatchPattern] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testMax() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Max.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Max.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Max] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testMaxLength() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, MaxLength.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, MaxLength.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [MaxLength] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testMaxSize() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, MaxSize.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, MaxSize.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [MaxSize] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testMemberOf() {
	}

	@Test
	public void testMin() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Min.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Min.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Min] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testMinLength() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, MinLength.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, MinLength.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [MinLength] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testMinSize() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, MinSize.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, MinSize.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [MinSize] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testNoSelfReference() {
	}

	@Test
	public void testNotBlank() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, NotBlank.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, NotBlank.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [NotBlank] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testNotEmpty() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, NotEmpty.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, NotEmpty.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [NotEmpty] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testNotEqual() {
	}

	@Test
	@Deprecated
	public void testNotEqualToField() {
	}

	@Test
	@Deprecated
	public void testNotMatchPattern() {
	}

	@Test
	@Deprecated
	public void testNotMemberOf() {
	}

	@Test
	public void testNotNegative() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, NotNegative.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, NotNegative.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [NotNegative] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testPast() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Past.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Past.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Past] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testRange() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Range.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Range.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Range] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testSize() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Size.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, Size.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Size] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	@Deprecated
	public void testValidateWithMethod() {
	}

	@Test
	public void testScriptAssert() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, ScriptAssert.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, ScriptAssert.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [ScriptAssert] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testAssertValid() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, InnerNotNull.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, InnerNotNull.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [AssertValid] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testAssertFieldConstraints() {
	}

	@Test
	@Deprecated
	public void testCheckWith() {
	}

	@Test
	public void testMethod() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, MethodTest.class);
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, MethodTest.class);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [Method/ReturnValue] check used " + used + "ms, avg. "
					+ ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	@Deprecated
	public void testMaxLengthWithTarget() {
	}

	@Test
	@Deprecated
	public void testMaxLengthWithJXPathTarget() {
	}

	@Test
	public void testZAll() {
		Set<ConstraintViolation<ObjectWithValidation>> violations = validator.validate(obj, Default.class);
		assertNotNull(violations);
		// assertEquals(violations.size(), 40);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("Hibernate Validator [All(" + violations.size() + ")] check used " + used + "ms, avg. "
					+ ((double) used) / 10000 + "ms.");
		}
	}
}
