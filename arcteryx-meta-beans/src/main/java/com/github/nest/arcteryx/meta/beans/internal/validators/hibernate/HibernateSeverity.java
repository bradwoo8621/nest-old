/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import javax.validation.Payload;

/**
 * severity
 * 
 * @author brad.wu
 */
public class HibernateSeverity {
	public interface INFO extends Payload {
	}

	public interface WARN extends Payload {
	}

	public interface ERROR extends Payload {
	}

	public interface FATAL extends Payload {
	}
}
