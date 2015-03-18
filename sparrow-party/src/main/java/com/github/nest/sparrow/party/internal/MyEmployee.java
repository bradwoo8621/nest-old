/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.generalization.IMyBranch;
import com.github.nest.sparrow.party.generalization.IMyDepartment;
import com.github.nest.sparrow.party.generalization.IMyEmployee;

/**
 * my employee
 * 
 * @author brad.wu
 */
public class MyEmployee extends IndividualAndRole implements IMyEmployee {
	private static final long serialVersionUID = -2292815680913274625L;

	private IMyBranch branch = null;
	private IMyDepartment department = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IEmployee#getDepartment()
	 */
	@Override
	public IMyDepartment getDepartment() {
		return this.department;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyEmployee#setDepartment(com.github.nest.sparrow.party.generalization.IMyDepartment)
	 */
	@Override
	public void setDepartment(IMyDepartment department) {
		this.department = department;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IEmployee#getBranch()
	 */
	@Override
	public IMyBranch getBranch() {
		return this.branch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyEmployee#setBranch(com.github.nest.sparrow.party.generalization.IMyBranch)
	 */
	@Override
	public void setBranch(IMyBranch branch) {
		this.branch = branch;
	}
}
