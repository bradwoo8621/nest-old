/**
 * 
 */
package com.github.nest.quelea.support;

import org.springframework.stereotype.Component;

import com.github.nest.quelea.model.Organization;

/**
 * organization party name strategy
 * 
 * @author brad.wu
 */
@Component("organizationStrategy")
public class OrganizationStrategy implements IPartyStrategy<Organization> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.support.IPartyStrategy#getPartyName(com.github.nest.quelea.model.Party)
	 */
	@Override
	public String getPartyName(Organization party) {
		return party.getOrganizationName();
	}
}
