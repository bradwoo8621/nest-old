/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * my branch
 * 
 * @author brad.wu
 */
public interface IMyBranch extends IOrganization, IPartyRole {
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IBranch#getParentBranch()
	 */
	IMyBranch getParentBranch();

	/**
	 * set parent branch
	 * 
	 * @param parentBranch
	 */
	void setParentBranch(IMyBranch parentBranch);
}
