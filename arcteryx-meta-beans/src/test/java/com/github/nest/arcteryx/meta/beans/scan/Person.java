/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.scan;

import java.util.Date;
import java.util.List;

import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBean;
import com.github.nest.arcteryx.meta.beans.annotation.ArcteryxBeanProperty;
import com.github.nest.arcteryx.meta.beans.annotation.AssertValid;
import com.github.nest.arcteryx.meta.beans.annotation.BeanScript;
import com.github.nest.arcteryx.meta.beans.annotation.DateRange;
import com.github.nest.arcteryx.meta.beans.annotation.Email;
import com.github.nest.arcteryx.meta.beans.annotation.Length;
import com.github.nest.arcteryx.meta.beans.annotation.NotBlank;
import com.github.nest.arcteryx.meta.beans.annotation.NotEmpty;
import com.github.nest.arcteryx.meta.beans.annotation.NotNegative;
import com.github.nest.arcteryx.meta.beans.annotation.NotNull;
import com.github.nest.arcteryx.meta.beans.annotation.NumberFormat;
import com.github.nest.arcteryx.meta.beans.annotation.Size;
import com.github.nest.arcteryx.meta.beans.annotation.TextFormat;
import com.github.nest.arcteryx.meta.beans.annotation.TheNumber;

/**
 * @author brad.wu
 *
 */
@ArcteryxBean(name = "Person", description = "A person")
@BeanScript(script = "groovy:_this.age > 0", profiles = { "beanscript" })
public class Person {
	@ArcteryxBeanProperty(description = "Name of person")
	@NotNull(profiles = { "notnull" })
	@NotBlank(profiles = { "notblank" })
	private String name = null;

	@ArcteryxBeanProperty(description = "Birthday of person")
	@DateRange(from = "1900/01/01", format = "yyyy/MM/dd", to = "today", profiles = "daterange")
	private Date birthday = null;

	@ArcteryxBeanProperty(description = "Email of person")
	@Email(profiles = "email")
	private String email = null;

	@ArcteryxBeanProperty(description = "Age of person")
	@TheNumber.List(values = { @TheNumber(min = 1, max = 200, profiles = "number") })
	@NotNegative(profiles = "notnegative")
	@NumberFormat(minIntegerDigits = 1, maxIntegerDigits = 3, maxFractionDigits = 0, profiles = "numberformat")
	private int age = 0;

	@ArcteryxBeanProperty(description = "Children of person")
	@Size(min = 1, profiles = "size")
	@AssertValid
	private List<Person> children = null;

	@ArcteryxBeanProperty(description = "Father of person")
	@AssertValid
	@TheNumber(min = 25, max = 200, target = "age", profiles = "target")
	private Person father = null;

	/**
	 * @return the name
	 */
	@NotEmpty(profiles = { "notempty" })
	@TextFormat(patterns = { "^([A-Z]+.*)$" }, profiles = { "textformat" })
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Length(min = 1, max = 30, profiles = "length")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the children
	 */
	public List<Person> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<Person> children) {
		this.children = children;
	}

	/**
	 * @return the father
	 */
	public Person getFather() {
		return father;
	}

	/**
	 * @param father
	 *            the father to set
	 */
	public void setFather(Person father) {
		this.father = father;
	}
}
