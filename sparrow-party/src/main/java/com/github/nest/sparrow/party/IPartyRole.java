/**
 * 
 */
package com.github.nest.sparrow.party;

/**
 * party role interface
 * 
 * @author brad.wu
 */
public interface IPartyRole extends IParty {
	/**
	 * get role type
	 * 
	 * @return
	 */
	IPartyRoleType getRoleType();

	/**
	 * is enabled or not?
	 * 
	 * @return
	 */
	boolean isEnabled();
}
