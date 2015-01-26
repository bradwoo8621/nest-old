/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import com.github.nest.arcteryx.meta.ResourceException;

/**
 * annotation define exception
 * 
 * @author brad.wu
 */
public class AnnotationDefineException extends ResourceException {
	private static final long serialVersionUID = -5228874305586077391L;

	public AnnotationDefineException() {
		super();
	}

	public AnnotationDefineException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnnotationDefineException(String message) {
		super(message);
	}

	public AnnotationDefineException(Throwable cause) {
		super(cause);
	}
}
