/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

/**
 * Individual name concatenator
 * 
 * @author brad.wu
 */
public final class IndividualNameConcatenator {
	/**
	 * concat name of individual, never return null.
	 * 
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return
	 */
	public static String concat(String firstName, String middleName, String lastName) {
		boolean isAsia = isAsia(firstName) || isAsia(middleName) || isAsia(lastName);
		if (isAsia) {
			return Joiner.on("").skipNulls().join(lastName, middleName, firstName);
		} else {
			return Joiner.on(" ").skipNulls().join(firstName, middleName, lastName);
		}
	}

	/**
	 * is asia, check the string has asia character or not
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isAsia(String name) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		for (int index = 0, count = name.length(); index < count; index++) {
			if (Character.UnicodeBlock.of(name.charAt(index)) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
				return true;
			}
		}
		return false;
	}
}
