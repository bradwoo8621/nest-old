/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * department in related organization
 * 
 * @author brad.wu
 */
public interface IRelatedDepartment extends IDepartment, IPartyRole {
	/**
	 * set branch
	 * 
	 * @param branch
	 */
	void setBranch(IOrganization branch);

	/**
	 * get parent department
	 * 
	 * @return
	 */
	IRelatedDepartment getParentDepartment();

	/**
	 * set parent department
	 * 
	 * @param department
	 */
	void setParentDepartment(IRelatedDepartment department);
}
