/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.ogn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.util.Locale;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * abstract object graph navigator
 * 
 * @author brad.wu
 */
public abstract class AbstractObjectGraphNavigator implements IObjectGraphNavigator {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * get field
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return the field or null if the field does not exist
	 */
	protected Field getField(final Class<?> clazz, final String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (final NoSuchFieldException e) {
			return null;
		}
	}

	/**
	 * get field
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	protected Field getFieldRecursive(final Class<?> clazz, final String fieldName) {
		final Field f = getField(clazz, fieldName);
		if (f != null)
			return f;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getFieldRecursive(superclazz, fieldName);
	}

	/**
	 * get getter
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	protected Method getGetterRecursive(final Class<?> clazz, final String propertyName) {
		final Method m = getGetter(clazz, propertyName);
		if (m != null)
			return m;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getGetterRecursive(superclazz, propertyName);
	}

	/**
	 * get getter
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	protected Method getGetter(final Class<?> clazz, final String propertyName) {
		final String appendix = propertyName.substring(0, 1).toUpperCase(Locale.getDefault())
				+ propertyName.substring(1);
		try {
			return clazz.getDeclaredMethod("get" + appendix);
		} catch (final NoSuchMethodException ex) {
			getLogger().trace("getXXX method not found.", ex);
		}
		try {
			return clazz.getDeclaredMethod("is" + appendix);
		} catch (final NoSuchMethodException ex) {
			getLogger().trace("isXXX method not found.", ex);
			return null;
		}
	}

	/**
	 *
	 * @param method
	 *            the method to invoke
	 * @param target
	 *            the object on which to invoke the method
	 * @param args
	 *            the method arguments
	 * @return the return value of the invoked method
	 */
	@SuppressWarnings("unchecked")
	public <T> T invokeMethod(final Method method, final Object target, final Object... args) {
		if (!method.isAccessible())
			AccessController.doPrivileged(new SetAccessibleAction(method));
		try {
			return (T) method.invoke(target, args);
		} catch (IllegalArgumentException e) {
			throw new ValidationException("Failed to get method " + getMemberName(target, method) + " value.", e);
		} catch (IllegalAccessException e) {
			throw new ValidationException("Failed to get method " + getMemberName(target, method) + " value.", e);
		} catch (InvocationTargetException e) {
			throw new ValidationException("Failed to get method " + getMemberName(target, method) + " value.", e);
		}
	}

	/**
	 * get field value
	 * 
	 * @param field
	 * @param target
	 * @return
	 */
	public Object getFieldValue(final Field field, final Object target) {
		if (!field.isAccessible())
			AccessController.doPrivileged(new SetAccessibleAction(field));
		try {
			return field.get(target);
		} catch (IllegalArgumentException e) {
			throw new ValidationException("Failed to get field " + getMemberName(target, field) + " value.", e);
		} catch (IllegalAccessException e) {
			throw new ValidationException("Failed to get field " + getMemberName(target, field) + " value.", e);
		}
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * get full qualified field name
	 * 
	 * @param target
	 * @param field
	 * @return
	 */
	protected String getMemberName(Object target, Field field) {
		return new StringBuilder(target.getClass().getName()).append('#').append(field.getName()).toString();
	}

	/**
	 * get full qualified method name
	 * 
	 * @param target
	 * @param method
	 * @return
	 */
	protected String getMemberName(Object target, Method method) {
		return new StringBuilder(target.getClass().getName()).append('#').append(method.getName()).toString();
	}
}
