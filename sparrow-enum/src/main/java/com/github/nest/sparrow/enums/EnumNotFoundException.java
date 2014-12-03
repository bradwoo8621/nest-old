/**
 * 
 */
package com.github.nest.sparrow.enums;

/**
 * enumeration not found exception
 * 
 * @author brad.wu
 */
public class EnumNotFoundException extends EnumException {
	private static final long serialVersionUID = 4830877140913567930L;

	public EnumNotFoundException() {
		super();
	}

	public EnumNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnumNotFoundException(String message) {
		super(message);
	}

	public EnumNotFoundException(Throwable cause) {
		super(cause);
	}
}
