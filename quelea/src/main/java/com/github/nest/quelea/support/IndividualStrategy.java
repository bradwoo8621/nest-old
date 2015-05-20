/**
 * 
 */
package com.github.nest.quelea.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.nest.quelea.model.Individual;
import com.google.common.base.Joiner;

/**
 * organization party name strategy
 * 
 * @author brad.wu
 */
@Component("individualStrategy")
public class IndividualStrategy implements IPartyStrategy<Individual> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.support.IPartyStrategy#getPartyName(com.github.nest.quelea.model.Party)
	 */
	@Override
	public String getPartyName(Individual party) {
		return this.concat(party.getFirstName(), party.getLastName());
	}

	/**
	 * concat name of individual, never return null.
	 * 
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return
	 */
	public String concat(String firstName, String lastName) {
		boolean isAsia = isAsia(firstName) || isAsia(lastName);
		if (isAsia) {
			return Joiner.on("").skipNulls().join(lastName, firstName);
		} else {
			return Joiner.on(" ").skipNulls().join(firstName, lastName);
		}
	}

	/**
	 * is asia, check the string has asia character or not
	 * 
	 * @param name
	 * @return
	 */
	public boolean isAsia(String name) {
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
