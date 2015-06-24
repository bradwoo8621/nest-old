/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

/**
 * response status, for customize the status of HTTP.
 * 
 * @author brad.wu
 */
public enum ResponseStatus implements StatusType {
	SERVER_EXCEPTION_RAISED(506, "Server exception raised");

	private final int code;
	private final String reason;
	private final Family family;

	ResponseStatus(final int statusCode, final String reasonPhrase) {
		this.code = statusCode;
		this.reason = reasonPhrase;
		this.family = Family.familyOf(statusCode);
	}

	/**
	 * Get the class of status code.
	 *
	 * @return the class of status code.
	 */
	@Override
	public Family getFamily() {
		return family;
	}

	/**
	 * Get the associated status code.
	 *
	 * @return the status code.
	 */
	@Override
	public int getStatusCode() {
		return code;
	}

	/**
	 * Get the reason phrase.
	 *
	 * @return the reason phrase.
	 */
	@Override
	public String getReasonPhrase() {
		return toString();
	}

	/**
	 * Get the reason phrase.
	 *
	 * @return the reason phrase.
	 */
	@Override
	public String toString() {
		return reason;
	}

	/**
	 * Convert a numerical status code into the corresponding Status.
	 *
	 * @param statusCode
	 *            the numerical status code.
	 * @return the matching Status or null is no matching Status is defined.
	 */
	public static ResponseStatus fromStatusCode(final int statusCode) {
		for (ResponseStatus s : ResponseStatus.values()) {
			if (s.code == statusCode) {
				return s;
			}
		}
		return null;
	}
}
