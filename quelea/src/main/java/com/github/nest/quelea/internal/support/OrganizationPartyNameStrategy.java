/**
 * 
 */
package com.github.nest.quelea.internal.support;

import org.springframework.stereotype.Component;

import com.github.nest.quelea.model.IOrganization;

/**
 * organization party name strategy
 * 
 * @author brad.wu
 */
@Component("organizationPartyNameStrategy")
public class OrganizationPartyNameStrategy implements IPartyNameStrategy<IOrganization> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.internal.support.IPartyNameStrategy#getPartyName(com.github.nest.quelea.model.IParty)
	 */
	@Override
	public String getPartyName(IOrganization party) {
		return party.getOrganizationName();
	}
}
