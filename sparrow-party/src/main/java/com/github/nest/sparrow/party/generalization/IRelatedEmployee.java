/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * employee in related organization
 * 
 * @author brad.wu
 */
public interface IRelatedEmployee extends IEmployee, IPartyRole {
	/**
	 * get department
	 * 
	 * @return
	 */
	IRelatedDepartment getDepartment();

	/**
	 * set department
	 * 
	 * @param department
	 */
	void setDepartment(IRelatedDepartment department);

	/**
	 * set branch
	 * 
	 * @param branch
	 */
	void setBranch(IOrganization branch);
}
