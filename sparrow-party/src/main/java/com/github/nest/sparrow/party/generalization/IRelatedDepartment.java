/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

/**
 * department in related organization
 * 
 * @author brad.wu
 */
public interface IRelatedDepartment extends IDepartment {
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
