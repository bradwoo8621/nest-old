/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.generalization.IMyBranch;
import com.github.nest.sparrow.party.generalization.IMyDepartment;

/**
 * my department
 * 
 * @author brad.wu
 */
public class MyDepartment extends OrganizationAndRole implements IMyDepartment {
	private static final long serialVersionUID = 2314876499743012462L;

	private IMyBranch branch = null;
	private IMyDepartment parentDepartment = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyDepartment#getBranch()
	 */
	@Override
	public IMyBranch getBranch() {
		return this.branch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyDepartment#setBranch(com.github.nest.sparrow.party.generalization.IMyBranch)
	 */
	@Override
	public void setBranch(IMyBranch branch) {
		this.branch = branch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyDepartment#getParentDepartment()
	 */
	@Override
	public IMyDepartment getParentDepartment() {
		return this.parentDepartment;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyDepartment#setParentDepartment(com.github.nest.sparrow.party.generalization.IMyDepartment)
	 */
	@Override
	public void setParentDepartment(IMyDepartment department) {
		this.parentDepartment = department;
	}
}
