/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IPartyRole;

/**
 * my employee
 * 
 * @author brad.wu
 */
public interface IMyEmployee extends IEmployee, IPartyRole {
	/**
	 * get department
	 * 
	 * @return
	 */
	IMyDepartment getDepartment();

	/**
	 * set department
	 * 
	 * @param department
	 */
	void setDepartment(IMyDepartment department);

	/**
	 * get branch
	 * 
	 * @return
	 */
	IMyBranch getBranch();

	/**
	 * set branch
	 * 
	 * @param branch
	 */
	void setBranch(IMyBranch branch);
}
