/**
 * 
 */
package com.github.nest.sparrow.party;

/**
 * internal party constants
 * 
 * @author brad.wu
 */
public interface IPartyConstants {
	/**
	 * the entity name prefix when a concrete party role class was registered as
	 * a party
	 */
	String DEFAULT_AS_PARTY_PREFIX = "AsParty";

	/**
	 * party context name, which will be used to register in repository
	 */
	String PARTY_CONTEXT_NAME = "sparrow-party-context";

	/**
	 * employee role code
	 */
	String ROLE_CODE_EMPLOYEE = "EMP";
}
