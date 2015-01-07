/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import java.util.Date;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * date range constraint. default value of {@linkplain #excludeFrom} and
 * {@linkplain #excludeTo} are false.
 * 
 * @author brad.wu
 */
public class DateRange extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 6400297560900055018L;

	private Date from = null;
	private boolean excludeFrom = false;
	private Date to = null;
	private boolean excludeTo = false;

	public DateRange(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

	/**
	 * @return the from
	 */
	protected Date getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	protected void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * @return the excludeFrom
	 */
	protected boolean isExcludeFrom() {
		return excludeFrom;
	}

	/**
	 * @param excludeFrom
	 *            the excludeFrom to set
	 */
	protected void setExcludeFrom(boolean excludeFrom) {
		this.excludeFrom = excludeFrom;
	}

	/**
	 * @return the to
	 */
	protected Date getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	protected void setTo(Date to) {
		this.to = to;
	}

	/**
	 * @return the excludeTo
	 */
	protected boolean isExcludeTo() {
		return excludeTo;
	}

	/**
	 * @param excludeTo
	 *            the excludeTo to set
	 */
	protected void setExcludeTo(boolean excludeTo) {
		this.excludeTo = excludeTo;
	}
}
