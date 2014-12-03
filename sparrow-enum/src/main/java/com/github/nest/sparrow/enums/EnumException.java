/**
 * 
 */
package com.github.nest.sparrow.enums;

/**
 * enumeration exception
 * 
 * @author brad.wu
 */
public class EnumException extends RuntimeException {
	private static final long serialVersionUID = 282834873361285381L;

	public EnumException() {
		super();
	}

	public EnumException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnumException(String message) {
		super(message);
	}

	public EnumException(Throwable cause) {
		super(cause);
	}
}
