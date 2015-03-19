/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.generalization.IRelatedBranch;
import com.github.nest.sparrow.party.generalization.IRelatedEmployee;

/**
 * related branch and role
 * 
 * @author brad.wu
 */
public abstract class RelatedBranchAndRole extends OrganizationAndRole implements IRelatedBranch {
	private static final long serialVersionUID = -929331264290048131L;

	private Boolean headOffice = Boolean.FALSE;
	private IOrganization parentBranch = null;
	private IRelatedEmployee artificialPerson = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IBranch#isHeadOffice()
	 */
	@Override
	public Boolean isHeadOffice() {
		return this.headOffice;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IBranch#setHeadOffice(java.lang.Boolean)
	 */
	@Override
	public void setHeadOffice(Boolean headOffice) {
		this.headOffice = headOffice;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedBranch#getParentBranch()
	 */
	@Override
	public IOrganization getParentBranch() {
		return this.parentBranch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedBranch#setParentBranch(com.github.nest.sparrow.party.generalization.IRelatedBranch)
	 */
	@Override
	public void setParentBranch(IOrganization parentBranch) {
		this.parentBranch = parentBranch;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedBranch#getArtificialPerson()
	 */
	@Override
	public IRelatedEmployee getArtificialPerson() {
		return this.artificialPerson;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IRelatedBranch#setArtificialPerson(com.github.nest.sparrow.party.generalization.IRelatedEmployee)
	 */
	@Override
	public void setArtificialPerson(IRelatedEmployee artificialPerson) {
		this.artificialPerson = artificialPerson;
	}
}
