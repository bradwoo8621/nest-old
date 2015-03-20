/**
 * 
 */
package com.github.nest.sparrow.party;

import com.github.nest.sparrow.party.generalization.IEducationOrganization;
import com.github.nest.sparrow.party.generalization.IMyBranch;
import com.github.nest.sparrow.party.generalization.IMyDepartment;
import com.github.nest.sparrow.party.generalization.IMyEmployee;
import com.github.nest.sparrow.party.generalization.IRelatedDepartment;
import com.github.nest.sparrow.party.generalization.IRelatedEmployee;

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
	/**
	 * my department role code
	 * 
	 * @see IMyDepartment
	 */
	String ROLE_CODE_MY_DEPARTMENT = "MDP";

	/**
	 * educational organization role code
	 * 
	 * @see IEducationOrganization
	 */
	String ROLE_CODE_EDUCATIONAL_ORGANIZATION = "EDU";

	/**
	 * related department role code
	 * 
	 * @see IRelatedDepartment
	 */
	String ROLE_CODE_RELATED_DEPARTMENT = "RDP";
	
	/**
	 * related employee role code
	 * 
	 * @see IRelatedEmployee
	 */
	String ROLE_CODE_RELATED_EMPLOYEE = "REP";
}
