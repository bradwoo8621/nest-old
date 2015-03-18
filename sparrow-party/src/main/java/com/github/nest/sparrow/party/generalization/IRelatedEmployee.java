/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

/**
 * employee in related organization
 * 
 * @author brad.wu
 */
public interface IRelatedEmployee extends IEmployee {
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
	 * get branch
	 * 
	 * @return
	 */
	IRelatedBranch getBranch();

	/**
	 * set branch
	 * 
	 * @param branch
	 */
	void setBranch(IRelatedBranch branch);
}
