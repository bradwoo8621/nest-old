/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.performance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.oval.ConstraintViolation;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import com.github.nest.arcteryx.validation.oval.OvalValidator;
import com.github.nest.arcteryx.validation.oval.configuration.xml.ClassPathDigesterXMLConfigurer;

/**
 * @author brad.wu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestObject {
	private static OvalValidator validator = null;
	private static ClassPathDigesterXMLConfigurer configurer = null;
	private static ObjectWithValidation obj = new ObjectWithValidation();
	private boolean runPeformance = true;

	@BeforeClass
	public static void before() throws IOException, SAXException {
		configurer = ClassPathDigesterXMLConfigurer.INSTANCE;
		InputStream is = TestObject.class.getResourceAsStream("test.xml");
		configurer.fromXML(is);
		is.close();

		validator = new OvalValidator(configurer);

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
		List<ConstraintViolation> violations = validator.validate(obj, "NotNullTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "TestObject.SequenceID.NotNull");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotNull.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotNullTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotNull] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testAssertTrue() {
		List<ConstraintViolation> violations = validator.validate(obj, "TrueValueTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.AssertTrue");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.AssertTrue.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "TrueValueTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [AssertTrue] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testAssertFalse() {
		List<ConstraintViolation> violations = validator.validate(obj, "FalseValueTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.AssertFalse");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.AssertFalse.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "FalseValueTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [AssertFalse] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testAssertNull() {
		List<ConstraintViolation> violations = validator.validate(obj, "NullValueTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.AssertNull");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.AssertNull.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NullValueTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [AssertNull] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testAssertURL() {
		List<ConstraintViolation> violations = validator.validate(obj, "URLTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.AssertURL");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.AssertURL.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "URLTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [AssertURL] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testDateRange() {
		List<ConstraintViolation> violations = validator.validate(obj, "DateRangeTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.DateRange");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.DateRange.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "DateRangeTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [DateRange] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testDigits() {
		List<ConstraintViolation> violations = validator.validate(obj, "DigitsTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Digits");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Digits.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "DigitsTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Digits] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testEmail() {
		List<ConstraintViolation> violations = validator.validate(obj, "EmailTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Email");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Email.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "EmailTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Email] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testEqualToField() {
		List<ConstraintViolation> violations = validator.validate(obj, "EqualToFieldTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.EqualToField");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.EqualToField.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "EqualToFieldTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out
					.println("OVal [EqualToField] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testFuture() {
		List<ConstraintViolation> violations = validator.validate(obj, "FutureTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Future");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Future.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "FutureTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Future] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testHasSubstring() {
		List<ConstraintViolation> violations = validator.validate(obj, "HasSubstringTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.HasSubstring");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.HasSubstring.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "HasSubstringTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out
					.println("OVal [HasSubstring] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testInstanceOf() {
		List<ConstraintViolation> violations = validator.validate(obj, "InstanceOfTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.InstanceOf");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.InstanceOf.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "InstanceOfTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [InstanceOf] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testInstanceOfAny() {
		List<ConstraintViolation> violations = validator.validate(obj, "InstanceOfAnyTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.InstanceOfAny");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.InstanceOfAny.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "InstanceOfAnyTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [InstanceOfAny] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testLength() {
		List<ConstraintViolation> violations = validator.validate(obj, "LengthTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Length");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Length.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "LengthTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Length] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMatchPattern() {
		List<ConstraintViolation> violations = validator.validate(obj, "MatchPatternTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MatchPattern");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MatchPattern.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MatchPatternTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out
					.println("OVal [MatchPattern] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMax() {
		List<ConstraintViolation> violations = validator.validate(obj, "MaxTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Max");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Max.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MaxTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Max] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMaxLength() {
		List<ConstraintViolation> violations = validator.validate(obj, "MaxLengthTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MaxLength");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MaxLength.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MaxLengthTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [MaxLength] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMaxSize() {
		List<ConstraintViolation> violations = validator.validate(obj, "MaxSizeTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MaxSize");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MaxSize.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MaxSizeTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [MaxSize] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMemberOf() {
		List<ConstraintViolation> violations = validator.validate(obj, "MemberOfTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MemberOf");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MemberOf.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MemberOfTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [MemberOf] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMin() {
		List<ConstraintViolation> violations = validator.validate(obj, "MinTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Min");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Min.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MinTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Min] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMinLength() {
		List<ConstraintViolation> violations = validator.validate(obj, "MinLengthTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MinLength");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MinLength.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MinLengthTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [MinLength] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMinSize() {
		List<ConstraintViolation> violations = validator.validate(obj, "MinSizeTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MinSize");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MinSize.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MinSizeTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [MinSize] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testNoSelfReference() {
		List<ConstraintViolation> violations = validator.validate(obj, "NoSelfReferenceTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NoSelfReference");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NoSelfReference.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NoSelfReferenceTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NoSelfReference] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testNotBlank() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotBlankTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotBlank");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotBlank.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotBlankTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotBlank] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testNotEmpty() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotEmptyTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotEmpty");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotEmpty.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotEmptyTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotEmpty] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testNotEqual() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotEqualTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotEqual");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotEqual.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotEqualTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotEqual] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testNotEqualToField() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotEqualToFieldTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotEqualToField");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotEqualToField.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotEqualToFieldTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotEqualToField] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testNotMatchPattern() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotMatchPatternTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotMatchPattern");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotMatchPattern.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotMatchPatternTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotMatchPattern] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testNotMemberOf() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotMemberOfTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotMemberOf");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotMemberOf.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotMemberOfTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotMemberOf] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testNotNegative() {
		List<ConstraintViolation> violations = validator.validate(obj, "NotNegativeTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotNegative");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotNegative.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "NotNegativeTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [NotNegative] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testPast() {
		List<ConstraintViolation> violations = validator.validate(obj, "PastTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Past");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Past.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "PastTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Past] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testRange() {
		List<ConstraintViolation> violations = validator.validate(obj, "RangeTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Range");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Range.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "RangeTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Range] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testSize() {
		List<ConstraintViolation> violations = validator.validate(obj, "SizeTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Size");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Size.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "SizeTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Size] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testValidateWithMethod() {
		List<ConstraintViolation> violations = validator.validate(obj, "ValidateWithMethodTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.ValidateWithMethod");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.ValidateWithMethod.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "ValidateWithMethodTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [ValidateWithMethod] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testAssert() {
		List<ConstraintViolation> violations = validator.validate(obj, "AssertCheckTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.Assert");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.Assert.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "AssertCheckTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Assert] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testAssertValid() {
		List<ConstraintViolation> violations = validator.validate(obj, "AssertValidTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.AssertValid");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.AssertValid.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "AssertValidTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [AssertValid] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testAssertFieldConstraints() {
		List<ConstraintViolation> violations = validator.validate(obj, "AssertFieldConstraintsTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotNull");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotNull.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "AssertFieldConstraintsTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [AssertFieldConstraints] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testCheckWith() {
		List<ConstraintViolation> violations = validator.validate(obj, "CheckWithTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.CheckWith");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.CheckWith.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "CheckWithTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [CheckWith] check used " + used + "ms, avg. " + ((double) used) / 10000 + "ms.");
		}
	}

	@Test
	public void testMethod() {
		List<ConstraintViolation> violations = validator.validate(obj, "MethodTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.NotNull");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.NotNull.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "MethodTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Method/ReturnValue] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testMaxLengthWithTarget() {
		List<ConstraintViolation> violations = validator.validate(obj, "TargetTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MaxLength");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MaxLength.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "TargetTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [Target/MaxLength] check used " + used + "ms, avg. " + ((double) used) / 10000
					+ "ms.");
		}
	}

	@Test
	public void testMaxLengthWithJXPathTarget() {
		List<ConstraintViolation> violations = validator.validate(obj, "JXPathTargetTest");
		assertNotNull(violations);
		assertEquals(violations.size(), 1);

		ConstraintViolation violation = violations.get(0);
		assertEquals(violation.getErrorCode(), "net.sf.oval.constraint.MaxLength");
		assertEquals(violation.getMessageTemplate(), "net.sf.oval.constraint.MaxLength.violated");

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj, "JXPathTargetTest");
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [JXPathTargetTest/MaxLength] check used " + used + "ms, avg. " + ((double) used)
					/ 10000 + "ms.");
		}
	}

	@Test
	public void testZAll() {
		List<ConstraintViolation> violations = validator.validate(obj);
		assertNotNull(violations);
		// assertEquals(violations.size(), 40);

		if (runPeformance) {
			long time = System.currentTimeMillis();
			for (int index = 0; index < 10000; index++) {
				validator.validate(obj);
			}
			long used = System.currentTimeMillis() - time;
			System.out.println("OVal [All(" + violations.size() + ")] check used " + used + "ms, avg. "
					+ ((double) used) / 10000 + "ms.");
		}
	}
}
