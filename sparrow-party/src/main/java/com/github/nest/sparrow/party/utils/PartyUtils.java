/**
 * 
 */
package com.github.nest.sparrow.party.utils;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.sparrow.party.IPartyConstants;

/**
 * party utilities
 * 
 * @author brad.wu
 */
public final class PartyUtils {
	private static String CONTEXT_NAME = IPartyConstants.PARTY_CONTEXT_NAME;

	/**
	 * get context name
	 * 
	 * @return
	 */
	public static String getContextName() {
		return StringUtils.isNotBlank(CONTEXT_NAME) ? CONTEXT_NAME : IPartyConstants.PARTY_CONTEXT_NAME;
	}

	/**
	 * set context name
	 * 
	 * @param contextName
	 */
	public static void setContextName(String contextName) {
		assert StringUtils.isNotBlank(contextName) : "Context name of party cannot be null or empty string.";

		CONTEXT_NAME = contextName;
	}
}
