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
	IDepartment getDepartment();

	/**
	 * get branch
	 * 
	 * @return
	 */
	IMyBranch getBranch();
}
