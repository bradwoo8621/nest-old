/**
 * 
 */
package com.github.nest.quelea;

import com.github.nest.arcteryx.common.NestRuntimeException;

/**
 * quelea runtime exception
 * 
 * @author brad.wu
 */
public class QueleaRuntimeException extends NestRuntimeException implements IQueleaExceptionCodes {
	private static final long serialVersionUID = -8793337683895060607L;

	public QueleaRuntimeException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public QueleaRuntimeException(String code, String message) {
		super(code, message);
	}

	public QueleaRuntimeException(String code, Throwable cause) {
		super(code, cause);
	}

	public QueleaRuntimeException(String code) {
		super(code);
	}
}
