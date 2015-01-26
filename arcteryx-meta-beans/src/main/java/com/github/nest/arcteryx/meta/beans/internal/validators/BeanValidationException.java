/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators;

import com.github.nest.arcteryx.meta.ResourceException;

/**
 * bean validation exception
 * 
 * @author brad.wu
 */
public class BeanValidationException extends ResourceException {
	private static final long serialVersionUID = 634034521771065209L;

	public BeanValidationException() {
		super();
	}

	public BeanValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanValidationException(String message) {
		super(message);
	}

	public BeanValidationException(Throwable cause) {
		super(cause);
	}
}
