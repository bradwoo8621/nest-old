/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.generalization.IMyBranch;

/**
 * my branch. "my" means the system owner.
 * 
 * @author brad.wu
 */
public class MyBranch extends OrganizationAndRole implements IMyBranch {
	private static final long serialVersionUID = -6854622605678822208L;

	private IMyBranch parentBranch = null;
	private Boolean headOffice = Boolean.FALSE;

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
	 * @param headOffice
	 *            the headOffice to set
	 */
	public void setHeadOffice(Boolean headOffice) {
		this.headOffice = headOffice;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IMyBranch#getParentBranch()
	 */
	@Override
	public IMyBranch getParentBranch() {
		return this.parentBranch;
	}

	/**
	 * @param parentBranch
	 *            the parentBranch to set
	 */
	public void setParentBranch(IMyBranch parentBranch) {
		this.parentBranch = parentBranch;
	}
}
