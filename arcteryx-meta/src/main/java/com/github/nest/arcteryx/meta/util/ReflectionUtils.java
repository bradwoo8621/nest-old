/**
 * 
 */
package com.github.nest.arcteryx.meta.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.ResourceException;

/**
 * reflection utils
 * 
 * @author brad.wu
 */
public class ReflectionUtils {
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	/**
	 * get getter in given class or its ancestors by given name
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public static Method getGetterRecursive(final Class<?> clazz, final String propertyName) {
		final Method m = getGetter(clazz, propertyName);
		if (m != null)
			return m;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getGetterRecursive(superclazz, propertyName);
	}

	/**
	 * get getter in given class by given name
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public static Method getGetter(final Class<?> clazz, final String propertyName) {
		final String appendix = propertyName.substring(0, 1).toUpperCase(Locale.getDefault())
				+ propertyName.substring(1);
		try {
			return clazz.getDeclaredMethod("get" + appendix);
		} catch (final NoSuchMethodException ex) {
			logger.trace("getXXX method not found.", ex);
		}
		try {
			return clazz.getDeclaredMethod("is" + appendix);
		} catch (final NoSuchMethodException ex) {
			logger.trace("isXXX method not found.", ex);
			return null;
		}
	}

	/**
	 * get field which declared in given class or its ancestors by given name
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldRecursive(final Class<?> clazz, final String fieldName) {
		final Field f = getField(clazz, fieldName);
		if (f != null)
			return f;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getFieldRecursive(superclazz, fieldName);
	}

	/**
	 * get field which declared in given class by given name
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field getField(final Class<?> clazz, final String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (final NoSuchFieldException e) {
			return null;
		}
	}

	/**
	 * get property name by method
	 * 
	 * @param getter
	 * @param silence
	 *            if true, skip the exception, return null when the method is
	 *            not neither getter nor setter
	 * @return
	 */
	public static String getPropertyName(Method getterOrSetter, boolean silence) {
		String name = getterOrSetter.getName();
		int length = name.length();
		if (StringUtils.startsWith(name, "set") && length > 3) {
			name = WordUtils.uncapitalize(StringUtils.substring(name, 3));
		} else if (StringUtils.startsWith(name, "is") && length > 2) {
			name = WordUtils.uncapitalize(StringUtils.substring(name, 2));
		} else if (StringUtils.startsWith(name, "get") && length > 3) {
			name = WordUtils.uncapitalize(StringUtils.substring(name, 3));
		} else {
			if (silence) {
				return null;
			}
			throw new ResourceException("Method [" + getterOrSetter.getClass().getName() + "#" + name
					+ "] is not a getter/setter method.");
		}
		return name;
	}

	/**
	 * get property name by method, will throw exception if the method is not
	 * neither getter nor setter.
	 * 
	 * @param getterOrSetter
	 * @return
	 */
	public static String getPropertyName(Method getterOrSetter) {
		return getPropertyName(getterOrSetter, false);
	}

	/**
	 * get setter in given class by given name
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public static Method getSetter(final Class<?> clazz, final String propertyName) {
		final String methodName = "set" + propertyName.substring(0, 1).toUpperCase(Locale.getDefault())
				+ propertyName.substring(1);

		final Method[] declaredMethods = clazz.getDeclaredMethods();
		for (final Method method : declaredMethods)
			if (methodName.equals(method.getName()) && method.getParameterTypes().length == 1)
				return method;
		logger.trace("No setter for {} not found on class {}.", propertyName, clazz);
		return null;
	}

	/**
	 * get setter in given class or its ancestors by given name
	 * 
	 * @param clazz
	 * @param propertyName
	 * @return
	 */
	public static Method getSetterRecursive(final Class<?> clazz, final String propertyName) {
		final Method m = getSetter(clazz, propertyName);
		if (m != null)
			return m;

		final Class<?> superclazz = clazz.getSuperclass();
		if (superclazz == null)
			return null;

		return getSetterRecursive(superclazz, propertyName);
	}
}
