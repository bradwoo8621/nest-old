/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

import com.github.nest.arcteryx.common.NestRuntimeException;

/**
 * codes runtime exception.<br>
 * 
 * @author brad.wu
 */
public class CodesRuntimeException extends NestRuntimeException {
	private static final long serialVersionUID = -32835228644574692L;

	public CodesRuntimeException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public CodesRuntimeException(String code, String message) {
		super(code, message);
	}

	public CodesRuntimeException(String code, Throwable cause) {
		super(code, cause);
	}

	public CodesRuntimeException(String code) {
		super(code);
	}
}
