/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

/**
 * date range constraint. default value of {@linkplain #excludeFrom} and
 * {@linkplain #excludeTo} are false.
 * 
 * @author brad.wu
 */
public class DateRange extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = 6400297560900055018L;

	private String from = null;
	private boolean excludeFrom = false;
	private String to = null;
	private boolean excludeTo = false;
	private String format = null;
	private int tolerance = 0;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the excludeFrom
	 */
	public boolean isExcludeFrom() {
		return excludeFrom;
	}

	/**
	 * @param excludeFrom
	 *            the excludeFrom to set
	 */
	public void setExcludeFrom(boolean excludeFrom) {
		this.excludeFrom = excludeFrom;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the excludeTo
	 */
	public boolean isExcludeTo() {
		return excludeTo;
	}

	/**
	 * @param excludeTo
	 *            the excludeTo to set
	 */
	public void setExcludeTo(boolean excludeTo) {
		this.excludeTo = excludeTo;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the tolerance
	 */
	public int getTolerance() {
		return tolerance;
	}

	/**
	 * @param tolerance
	 *            the tolerance to set
	 */
	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}
}
