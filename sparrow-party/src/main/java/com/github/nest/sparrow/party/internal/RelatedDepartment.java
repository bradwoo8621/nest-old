/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;
import com.github.nest.sparrow.party.generalization.IRelatedDepartment;

/**
 * related department
 * 
 * @author brad.wu
 */
public class RelatedDepartment extends OrganizationAndRole implements IRelatedDepartment {
	private static final long serialVersionUID = -2199869907938106894L;

	private IOrganization branch = null;
	private IRelatedDepartment parentDepartment = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IDepartment#getBranch()
	 */
	@Override
	public IOrganization getBranch() {
		return this.branch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedDepartment#setBranch(com.github.nest.sparrow.party.IOrganization)
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
	 * @see com.github.nest.sparrow.party.generalization.IRelatedDepartment#getParentDepartment()
	 */
	@Override
	public IRelatedDepartment getParentDepartment() {
		return this.parentDepartment;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedDepartment#setParentDepartment(com.github.nest.sparrow.party.generalization.IRelatedDepartment)
	 */
	@Override
	public void setParentDepartment(IRelatedDepartment department) {
		this.parentDepartment = department;
	}
}
