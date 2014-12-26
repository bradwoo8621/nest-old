/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.ogn;

import java.lang.reflect.AccessibleObject;
import java.security.PrivilegedAction;

/**
 * Set accessible action
 * 
 * @author brad.wu
 */
public final class SetAccessibleAction implements PrivilegedAction<AccessibleObject> {
	private AccessibleObject ao;

	public SetAccessibleAction(final AccessibleObject ao) {
		this.ao = ao;
	}

	/**
	 * {@inheritDoc}
	 */
	public AccessibleObject run() {
		ao.setAccessible(true);
		return ao;
	}
}