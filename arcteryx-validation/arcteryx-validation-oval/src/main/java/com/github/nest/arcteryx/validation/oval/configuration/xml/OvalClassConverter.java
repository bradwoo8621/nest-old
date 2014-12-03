/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.xml;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * OVal class converter for install into apache common utilities for enhance the
 * primitive types support
 * 
 * @author brad.wu
 */
public class OvalClassConverter extends AbstractConverter {
	private static Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

	static {
		classMap.put("int", int.class);
		classMap.put("long", long.class);
		classMap.put("short", short.class);
		classMap.put("double", double.class);
		classMap.put("float", float.class);
		classMap.put("char", char.class);
		classMap.put("boolean", boolean.class);
		classMap.put("byte", byte.class);

		classMap.put("int[]", int[].class);
		classMap.put("long[]", long[].class);
		classMap.put("short[]", short[].class);
		classMap.put("double[]", double[].class);
		classMap.put("float[]", float[].class);
		classMap.put("char[]", char[].class);
		classMap.put("boolean[]", boolean[].class);
		classMap.put("byte[]", byte[].class);
	}

	public OvalClassConverter() {
		super();
	}

	public OvalClassConverter(Object defaultValue) {
		super(defaultValue);
	}

	/**
	 * Return the default type this <code>Converter</code> handles.
	 *
	 * @return The default type this <code>Converter</code> handles.
	 * @since 1.8.0
	 */
	protected Class<?> getDefaultType() {
		return Class.class;
	}

	/**
	 * <p>
	 * Convert a java.lang.Class or object into a String.
	 * </p>
	 *
	 * @param value
	 *            The input value to be converted
	 * @return the converted String value.
	 * @since 1.8.0
	 */
	protected String convertToString(Object value) {
		return (value instanceof Class) ? ((Class<?>) value).getName() : value.toString();
	}

	/**
	 * <p>
	 * Convert the input object into a java.lang.Class.
	 * </p>
	 *
	 * @param type
	 *            Data type to which this value should be converted.
	 * @param value
	 *            The input value to be converted.
	 * @return The converted value.
	 * @throws Throwable
	 *             if an error occurs converting to the specified type
	 * @since 1.8.0
	 */
	@SuppressWarnings("rawtypes")
	protected Object convertToType(Class type, Object value) throws Throwable {
		Class<?> primitiveClass = classMap.get(value.toString());
		if (primitiveClass != null) {
			return primitiveClass;
		}

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			try {
				return (classLoader.loadClass(value.toString()));
			} catch (ClassNotFoundException ex) {
				// Don't fail, carry on and try this class's class loader
				// (see issue# BEANUTILS-263)
			}
		}

		// Try this class's class loader
		classLoader = OvalClassConverter.class.getClassLoader();
		return (classLoader.loadClass(value.toString()));
	}
}
