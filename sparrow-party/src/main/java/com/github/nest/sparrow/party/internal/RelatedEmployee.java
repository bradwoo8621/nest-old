/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;
import com.github.nest.sparrow.party.generalization.IRelatedDepartment;
import com.github.nest.sparrow.party.generalization.IRelatedEmployee;

/**
 * related employee
 * 
 * @author brad.wu
 */
public class RelatedEmployee extends IndividualAndRole implements IRelatedEmployee {
	private static final long serialVersionUID = 6036045778548743643L;

	private IOrganization branch = null;
	private IRelatedDepartment department = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IEmployee#getBranch()
	 */
	@Override
	public IOrganization getBranch() {
		return this.branch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedEmployee#setBranch(com.github.nest.sparrow.party.IOrganization)
	 */
	@Override
	public void setBranch(IOrganization branch) {
		if (branch != null && branch instanceof IPartyRole) {
			this.branch = (IOrganization) ((IPartyRole) branch).getParty();
		} else {
			this.branch = branch;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedEmployee#getDepartment()
	 */
	@Override
	public IRelatedDepartment getDepartment() {
		return this.department;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedEmployee#setDepartment(com.github.nest.sparrow.party.generalization.IRelatedDepartment)
	 */
	@Override
	public void setDepartment(IRelatedDepartment department) {
		this.department = department;
	}
}
