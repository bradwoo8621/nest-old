/**
 * 
 */
package com.github.nest.arcteryx.meta;

/**
 * resource exception
 * 
 * @author brad.wu
 */
public class ResourceException extends RuntimeException {
	private static final long serialVersionUID = -6600660468657813819L;

	public ResourceException() {
		super();
	}

	public ResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(Throwable cause) {
		super(cause);
	}
}
