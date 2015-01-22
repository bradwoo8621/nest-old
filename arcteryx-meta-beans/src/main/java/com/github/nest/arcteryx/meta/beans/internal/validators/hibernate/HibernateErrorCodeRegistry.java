/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate;

import java.util.HashMap;
import java.util.Map;

/**
 * hibernate error code registry
 * 
 * @author brad.wu
 */
public final class HibernateErrorCodeRegistry {
	private final static Map<Class<?>, String> errorCodes = new HashMap<Class<?>, String>();

	private HibernateErrorCodeRegistry() {
	}

	/**
	 * register error code
	 * 
	 * @param annotationClass
	 * @param errorCode
	 */
	public static final void registerErrorCode(Class<?> annotationClass, String errorCode) {
		errorCodes.put(annotationClass, errorCode);
	}

	/**
	 * get error code
	 * 
	 * @param annotationClass
	 * @return
	 */
	public static final String getErrorCode(Class<?> annotationClass) {
		return errorCodes.get(annotationClass);
	}
}
