/**
 * 
 */
package com.github.nest.sparrow.party;

import com.github.nest.sparrow.party.generalization.IMyBranch;
import com.github.nest.sparrow.party.generalization.IMyEmployee;

/**
 * internal party constants
 * 
 * @author brad.wu
 */
public interface IPartyConstants {
	/**
	 * party context name, which will be used to register in repository
	 */
	String PARTY_CONTEXT_NAME = "sparrow-party-context";

	/**
	 * my employee role code
	 * 
	 * @see IMyEmployee
	 */
	String ROLE_CODE_MY_EMPLOYEE = "MEM";
	/**
	 * my branch role code
	 * 
	 * @see IMyBranch
	 */
	String ROLE_CODE_MY_BRANCH = "MBR";

}
