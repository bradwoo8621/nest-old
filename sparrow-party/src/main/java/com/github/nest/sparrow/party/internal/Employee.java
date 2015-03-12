/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.generalization.IBranch;
import com.github.nest.sparrow.party.generalization.IDepartment;
import com.github.nest.sparrow.party.generalization.IEmpolyee;

/**
 * employee
 * 
 * @author brad.wu
 */
public class Employee extends IndividualAndRole implements IEmpolyee {
	private static final long serialVersionUID = -2292815680913274625L;

	private IBranch branch = null;
	private IDepartment department = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IEmpolyee#getDepartment()
	 */
	@Override
	public IDepartment getDepartment() {
		return this.department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(IDepartment department) {
		this.department = department;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IEmpolyee#getBranch()
	 */
	@Override
	public IBranch getBranch() {
		return this.branch;
	}

	/**
	 * @param branch
	 *            the branch to set
	 */
	public void setBranch(IBranch branch) {
		this.branch = branch;
	}
}
