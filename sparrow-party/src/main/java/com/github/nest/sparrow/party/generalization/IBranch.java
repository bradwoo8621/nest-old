/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IOrganization;

/**
 * branch
 * 
 * @author brad.wu
 */
public interface IBranch extends IOrganization {
	/**
	 * get parent branch
	 * 
	 * @return
	 */
	IBranch getParentBranch();

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
}
