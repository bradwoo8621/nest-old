/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IPartyRole;

/**
 * my department
 * 
 * @author brad.wu
 */
public interface IMyDepartment extends IDepartment, IPartyRole {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IDepartment#getBranch()
	 */
	IMyBranch getBranch();

	/**
	 * set branch
	 * 
	 * @param branch
	 */
	void setBranch(IMyBranch branch);

	/**
	 * get parent department
	 * 
	 * @return
	 */
	IMyDepartment getParentDepartment();

	/**
	 * set parent department
	 * 
	 * @param department
	 */
	void setParentDepartment(IMyDepartment department);
}
