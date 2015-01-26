/**
 * 
 */
package com.github.nest.arcteryx.meta.util;

import java.util.List;

import static org.apache.commons.lang3.ClassUtils.getAllSuperclasses;
import static org.apache.commons.lang3.ClassUtils.getAllInterfaces;

/**
 * Class utilities
 * 
 * @author brad.wu
 */
public class ClassUtils {
	/**
	 * classes has higher priority than interfaces
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> getAncestorClasses(Class<?> clazz) {
		List<Class<?>> list = getAllSuperclasses(clazz);
		list.add(0, clazz);
		list.addAll(getAllInterfaces(clazz));
		return list;
	}
}
