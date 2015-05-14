/**
 * 
 */
package com.github.nest.arcteryx.common;

import org.apache.commons.lang3.StringUtils;

/**
 * runtime exception with code
 * 
 * @author brad.wu
 */
public class NestRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -5438426968697469651L;

	private String code = null;

	public NestRuntimeException(String code) {
		super();
		this.setCode(code);
	}

	public NestRuntimeException(String code, String message, Throwable cause) {
		super(message, cause);
		this.setCode(code);
	}

	public NestRuntimeException(String code, String message) {
		super(message);
		this.setCode(code);
	}

	public NestRuntimeException(String code, Throwable cause) {
		super(cause);
		this.setCode(code);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	private void setCode(String code) {
		assert !StringUtils.isBlank(code) : "Code of NestException cannot be blank.";

		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (message == null) {
			return this.getCode();
		} else {
			return new StringBuilder(this.getCode()).append(" : ").append(message).toString();
		}
	}
}
