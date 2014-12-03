/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.performance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
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

/**
 * @author brad.wu
 */
@ScriptAssert(lang = "groovy", script = "_this.assertCheck ==_this.email || _this.assertCheck.endsWith('.com')", groups = {
		Default.class, ScriptAssert.class })
public class ObjectWithValidation {
	@NotNull(groups = { Default.class, NotNull.class })
	private Long notNull = null;
	@AssertTrue(groups = { Default.class, AssertTrue.class })
	private boolean trueValue = false;
	@AssertFalse(groups = { Default.class, AssertFalse.class })
	private boolean falseValue = true;
	@Null(groups = { Default.class, Null.class })
	private String nullValue = "abc";
	@URL(groups = { Default.class, URL.class }, protocol = "https")
	private String url = "http://www.baidu.com";
	private Date dateRange = null;
	@Digits(integer = 2, fraction = 2, groups = { Default.class, Digits.class })
	private BigDecimal digits = new BigDecimal("100.123");
	@Email(groups = { Default.class, Email.class })
	private String email = "abc.def@kkk.";
	private String equalToField = "abc.def@kkj";
	@Future(groups = { Default.class, Future.class })
	private Date future = null;
	private String substring = "abcdefghijklmnopqrstuvwxyz";
	private java.sql.Date instanceOf = new java.sql.Date(System.currentTimeMillis());
	private java.sql.Date instanceOfAny = new java.sql.Date(System.currentTimeMillis());
	@Length(max = 5, min = 2, groups = { Default.class, Length.class })
	private String length = "length";
	@Pattern(groups = { Default.class, Pattern.class }, regexp = "a{4}")
	private String matchPattern = "abc";
	@Max(groups = { Default.class, Max.class }, value = 90)
	private Double max = 100d;
	@Length(max = 4, groups = { Default.class, MaxLength.class })
	private String maxLength = "abcde";
	@Size(max = 2, groups = { Default.class, MaxSize.class })
	private List<Object> maxSize = null;
	private String memberOf = "memberOf";
	@Min(value = 200, groups = { Default.class, Min.class })
	private Double min = 100d;
	@Length(min = 10, groups = { Default.class, MinLength.class })
	private String minLength = "abcde";
	@Size(min = 5, groups = { Default.class, MinSize.class })
	private List<Object> minSize = null;
	private ObjectWithValidation noSelfReference = this;
	@NotBlank(groups = { Default.class, NotBlank.class })
	private String notBlank = " ";
	@NotEmpty(groups = { Default.class, NotEmpty.class })
	private String notEmpty = "";
	private String notEqual = "abc";
	private String notEqualToField = "abc.def@kkk";
	private String notMatchPattern = "abc";
	private String notMemberOf = "notMemberOf";
	@Min(value = 0, groups = { Default.class, NotNegative.class })
	private String notNegative = "-1.23";
	@Past(groups = { Default.class, Past.class })
	private Date past = null;
	@Range(min = 2, max = 10, groups = { Default.class, Range.class })
	private Double range = 1.23d;
	@Size(min = 2, max = 5, groups = { Default.class, Size.class })
	private Map<Object, Object> size = null;

	private String assertCheck = "abc.def@kkk.co";
	@Valid
	private InnerObjectWithValidation assertValid = new InnerObjectWithValidation();
	private InnerObjectWithValidation assertFieldConstraints = null;
	private String checkWith = "";

	private String when1 = "abc";
	private String when2 = "def";

	private String method = null;

	/**
	 * @return the notNull
	 */
	public Long getNotNull() {
		return notNull;
	}

	/**
	 * @param notNull
	 *            the notNull to set
	 */
	public void setNotNull(Long notNull) {
		this.notNull = notNull;
	}

	/**
	 * @return the trueValue
	 */
	public boolean isTrueValue() {
		return trueValue;
	}

	/**
	 * @param trueValue
	 *            the trueValue to set
	 */
	public void setTrueValue(boolean trueValue) {
		this.trueValue = trueValue;
	}

	/**
	 * @return the falseValue
	 */
	public boolean isFalseValue() {
		return falseValue;
	}

	/**
	 * @param falseValue
	 *            the falseValue to set
	 */
	public void setFalseValue(boolean falseValue) {
		this.falseValue = falseValue;
	}

	/**
	 * @return the nullValue
	 */
	public String getNullValue() {
		return nullValue;
	}

	/**
	 * @param nullValue
	 *            the nullValue to set
	 */
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the dateRange
	 */
	public Date getDateRange() {
		return dateRange;
	}

	/**
	 * @param dateRange
	 *            the dateRange to set
	 */
	public void setDateRange(Date dateRange) {
		this.dateRange = dateRange;
	}

	/**
	 * @return the digits
	 */
	public BigDecimal getDigits() {
		return digits;
	}

	/**
	 * @param digits
	 *            the digits to set
	 */
	public void setDigits(BigDecimal digits) {
		this.digits = digits;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the equalToField
	 */
	public String getEqualToField() {
		return equalToField;
	}

	/**
	 * @param equalToField
	 *            the equalToField to set
	 */
	public void setEqualToField(String equalToField) {
		this.equalToField = equalToField;
	}

	/**
	 * @return the future
	 */
	public Date getFuture() {
		return future;
	}

	/**
	 * @param future
	 *            the future to set
	 */
	public void setFuture(Date future) {
		this.future = future;
	}

	/**
	 * @return the substring
	 */
	public String getSubstring() {
		return substring;
	}

	/**
	 * @param substring
	 *            the substring to set
	 */
	public void setSubstring(String substring) {
		this.substring = substring;
	}

	/**
	 * @return the instanceOf
	 */
	public java.sql.Date getInstanceOf() {
		return instanceOf;
	}

	/**
	 * @param instanceOf
	 *            the instanceOf to set
	 */
	public void setInstanceOf(java.sql.Date instanceOf) {
		this.instanceOf = instanceOf;
	}

	/**
	 * @return the instanceOfAny
	 */
	public java.sql.Date getInstanceOfAny() {
		return instanceOfAny;
	}

	/**
	 * @param instanceOfAny
	 *            the instanceOfAny to set
	 */
	public void setInstanceOfAny(java.sql.Date instanceOfAny) {
		this.instanceOfAny = instanceOfAny;
	}

	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * @return the matchPattern
	 */
	public String getMatchPattern() {
		return matchPattern;
	}

	/**
	 * @param matchPattern
	 *            the matchPattern to set
	 */
	public void setMatchPattern(String matchPattern) {
		this.matchPattern = matchPattern;
	}

	/**
	 * @return the max
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 * @return the maxLength
	 */
	public String getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 *            the maxLength to set
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return the maxSize
	 */
	public List<Object> getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize
	 *            the maxSize to set
	 */
	public void setMaxSize(List<Object> maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the memberOf
	 */
	public String getMemberOf() {
		return memberOf;
	}

	/**
	 * @param memberOf
	 *            the memberOf to set
	 */
	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}

	/**
	 * @return the min
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * @return the minLength
	 */
	public String getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 *            the minLength to set
	 */
	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the minSize
	 */
	public List<Object> getMinSize() {
		return minSize;
	}

	/**
	 * @param minSize
	 *            the minSize to set
	 */
	public void setMinSize(List<Object> minSize) {
		this.minSize = minSize;
	}

	/**
	 * @return the noSelfReference
	 */
	public ObjectWithValidation getNoSelfReference() {
		return noSelfReference;
	}

	/**
	 * @param noSelfReference
	 *            the noSelfReference to set
	 */
	public void setNoSelfReference(ObjectWithValidation noSelfReference) {
		this.noSelfReference = noSelfReference;
	}

	/**
	 * @return the notBlank
	 */
	public String getNotBlank() {
		return notBlank;
	}

	/**
	 * @param notBlank
	 *            the notBlank to set
	 */
	public void setNotBlank(String notBlank) {
		this.notBlank = notBlank;
	}

	/**
	 * @return the notEmpty
	 */
	public String getNotEmpty() {
		return notEmpty;
	}

	/**
	 * @param notEmpty
	 *            the notEmpty to set
	 */
	public void setNotEmpty(String notEmpty) {
		this.notEmpty = notEmpty;
	}

	/**
	 * @return the notEqual
	 */
	public String getNotEqual() {
		return notEqual;
	}

	/**
	 * @param notEqual
	 *            the notEqual to set
	 */
	public void setNotEqual(String notEqual) {
		this.notEqual = notEqual;
	}

	/**
	 * @return the notEqualToField
	 */
	public String getNotEqualToField() {
		return notEqualToField;
	}

	/**
	 * @param notEqualToField
	 *            the notEqualToField to set
	 */
	public void setNotEqualToField(String notEqualToField) {
		this.notEqualToField = notEqualToField;
	}

	/**
	 * @return the notMatchPattern
	 */
	public String getNotMatchPattern() {
		return notMatchPattern;
	}

	/**
	 * @param notMatchPattern
	 *            the notMatchPattern to set
	 */
	public void setNotMatchPattern(String notMatchPattern) {
		this.notMatchPattern = notMatchPattern;
	}

	/**
	 * @return the notMemberOf
	 */
	public String getNotMemberOf() {
		return notMemberOf;
	}

	/**
	 * @param notMemberOf
	 *            the notMemberOf to set
	 */
	public void setNotMemberOf(String notMemberOf) {
		this.notMemberOf = notMemberOf;
	}

	/**
	 * @return the notNegative
	 */
	public String getNotNegative() {
		return notNegative;
	}

	/**
	 * @param notNegative
	 *            the notNegative to set
	 */
	public void setNotNegative(String notNegative) {
		this.notNegative = notNegative;
	}

	/**
	 * @return the past
	 */
	public Date getPast() {
		return past;
	}

	/**
	 * @param past
	 *            the past to set
	 */
	public void setPast(Date past) {
		this.past = past;
	}

	/**
	 * @return the range
	 */
	public Double getRange() {
		return range;
	}

	/**
	 * @param range
	 *            the range to set
	 */
	public void setRange(Double range) {
		this.range = range;
	}

	/**
	 * @return the size
	 */
	public Map<Object, Object> getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Map<Object, Object> size) {
		this.size = size;
	}

	public boolean validateWithMethod(String email) {
		return email == null || email.endsWith("github.com");
	}

	/**
	 * @return the assertCheck
	 */
	public String getAssertCheck() {
		return assertCheck;
	}

	/**
	 * @param assertCheck
	 *            the assertCheck to set
	 */
	public void setAssertCheck(String assertCheck) {
		this.assertCheck = assertCheck;
	}

	/**
	 * @return the assertValid
	 */
	public InnerObjectWithValidation getAssertValid() {
		return assertValid;
	}

	/**
	 * @param assertValid
	 *            the assertValid to set
	 */
	public void setAssertValid(InnerObjectWithValidation assertValid) {
		this.assertValid = assertValid;
	}

	/**
	 * @return the assertFieldConstraints
	 */
	public InnerObjectWithValidation getAssertFieldConstraints() {
		return assertFieldConstraints;
	}

	/**
	 * @param assertFieldConstraints
	 *            the assertFieldConstraints to set
	 */
	public void setAssertFieldConstraints(InnerObjectWithValidation assertFieldConstraints) {
		this.assertFieldConstraints = assertFieldConstraints;
	}

	/**
	 * @return the checkWith
	 */
	public String getCheckWith() {
		return checkWith;
	}

	/**
	 * @param checkWith
	 *            the checkWith to set
	 */
	public void setCheckWith(String checkWith) {
		this.checkWith = checkWith;
	}

	/**
	 * @return the when1
	 */
	public String getWhen1() {
		return when1;
	}

	/**
	 * @param when1
	 *            the when1 to set
	 */
	public void setWhen1(String when1) {
		this.when1 = when1;
	}

	/**
	 * @return the when2
	 */
	public String getWhen2() {
		return when2;
	}

	/**
	 * @param when2
	 *            the when2 to set
	 */
	public void setWhen2(String when2) {
		this.when2 = when2;
	}

	/**
	 * @return the method
	 */
	@NotNull(groups = { Default.class, MethodTest.class })
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
}
