/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.performance;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * @author brad.wu
 */
public class InnerObjectWithValidation {
	@NotNull(groups = { Default.class, InnerNotNull.class })
	private String notNull = null;
	private String maxLength = "abcdef";
	private List<InnerObjectWithValidation> list = new ArrayList<InnerObjectWithValidation>();

	/**
	 * @return the notNull
	 */
	public String getNotNull() {
		return notNull;
	}

	/**
	 * @param notNull
	 *            the notNull to set
	 */
	public void setNotNull(String notNull) {
		this.notNull = notNull;
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
	 * @return the list
	 */
	public List<InnerObjectWithValidation> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<InnerObjectWithValidation> list) {
		this.list = list;
	}
}
