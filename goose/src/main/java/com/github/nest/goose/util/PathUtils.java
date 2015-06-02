/**
 * 
 */
package com.github.nest.goose.util;

import org.apache.commons.lang3.StringUtils;

/**
 * file utils
 * 
 * @author brad.wu
 */
public class PathUtils {
	/**
	 * return true when given path starts with "classpath:"
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isClasspath(String path) {
		return StringUtils.startsWith(path, "classpath:");
	}

	/**
	 * get absolute classpath
	 * 
	 * @param path
	 * @return
	 */
	public static String getAbsoluteClasspath(String path) {
		if (StringUtils.startsWith(path, "classpath:/")) {
			return StringUtils.replace(path, "classpath:", "");
		} else if (StringUtils.startsWith(path, "classpath:")) {
			return StringUtils.replace(path, "classpath:", "/");
		} else {
			return path;
		}
	}
}
