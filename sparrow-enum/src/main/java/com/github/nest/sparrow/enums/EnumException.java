/**
 * 
 */
package com.github.nest.sparrow.enums;

import com.github.nest.arcteryx.meta.ResourceException;

/**
 * enumeration exception
 * 
 * @author brad.wu
 */
public class EnumException extends ResourceException {
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
