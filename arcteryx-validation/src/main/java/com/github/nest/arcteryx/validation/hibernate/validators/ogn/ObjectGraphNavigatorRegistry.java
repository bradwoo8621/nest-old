/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.validators.ogn;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationException;

import org.slf4j.LoggerFactory;

/**
 * object graph navigator registry, copy from OVal
 * 
 * @author brad.wu
 */
public final class ObjectGraphNavigatorRegistry {
	private static final String OGN_DEFAULT = "";
	public static final String OGN_JXPATH = "jxpath";
	
	private static Map<String, IObjectGraphNavigator> cache = new HashMap<String, IObjectGraphNavigator>();

	static {
		initializeDefaultOGN(OGN_JXPATH);
		initializeDefaultOGN(OGN_DEFAULT);
	}

	/**
	 * initialize default navigator
	 * 
	 * @param id
	 * @return
	 */
	private static IObjectGraphNavigator initializeDefaultOGN(final String id) {
		// JXPath support
		if (OGN_JXPATH.equals(id) && isClassPresent("org.apache.commons.jxpath.JXPathContext"))
			return registerObjectGraphNavigator(OGN_JXPATH, new ObjectGraphNavigatorJXPathImpl());

		if (OGN_DEFAULT.equals(id))
			return registerObjectGraphNavigator(OGN_DEFAULT, new ObjectGraphNavigatorDefaultImpl());
		return null;
	}

	/**
	 * get navigator by given id
	 * 
	 * @param id
	 * @return
	 */
	public static IObjectGraphNavigator getObjectGraphNavigator(final String id) {
		assert id != null : "ID of navigator cannot be null.";

		IObjectGraphNavigator ogn = cache.get(id);

		if (ogn == null)
			ogn = initializeDefaultOGN(id);

		if (ogn == null)
			throw new ValidationException("Object graph navigator [" + id + "] not found.");

		return ogn;
	}

	/**
	 * register object graph navigator
	 * 
	 * @param id
	 * @param ogn
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static IObjectGraphNavigator registerObjectGraphNavigator(final String id, final IObjectGraphNavigator ogn)
			throws IllegalArgumentException {
		assert id != null : "ID of navigator cannot be null.";
		assert ogn != null : "Navigator cannot be null.";

		LoggerFactory.getLogger(ObjectGraphNavigatorRegistry.class).info(
				"Object Graph Navigator '{1}' registered: {2}", id, ogn);

		cache.put(id, ogn);
		return ogn;
	}

	private static boolean isClassPresent(final String className) {
		try {
			Class.forName(className);
			return true;
		} catch (final ClassNotFoundException e) {
			return false;
		}
	}
}
