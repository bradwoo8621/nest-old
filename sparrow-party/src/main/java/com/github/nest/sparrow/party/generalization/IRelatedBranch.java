/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * related organization, not me.
 * 
 * @author brad.wu
 */
public interface IRelatedBranch extends IOrganization, IPartyRole {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IBranch#getParentBranch()
	 */
	IOrganization getParentBranch();

	/**
	 * set parent branch
	 * 
	 * @param parentBranch
	 */
	void setParentBranch(IOrganization parentBranch);

	/**
	 * is head office or not. if true, no parent branch.
	 * 
	 * @return
	 */
	Boolean isHeadOffice();

	/**
	 * 
	 * @param headOffice
	 */
	void setHeadOffice(Boolean headOffice);

	/**
	 * get artificial person(法人代表)
	 * 
	 * @return
	 */
	IRelatedEmployee getArtificialPerson();

	/**
	 * 
	 * @param artificialPerson
	 */
	void setArtificialPerson(IRelatedEmployee artificialPerson);
}
