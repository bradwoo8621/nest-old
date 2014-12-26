/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.util;

/**
 * class utilities
 * 
 * @author brad.wu
 */
public class ClassUtil {
	public static boolean isClassPresent(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (final ClassNotFoundException e) {
			return false;
		}
	}
}
