/**
 * 
 */
package com.github.nest.sparrow.party.generalization;


/**
 * related organization, not me.
 * 
 * @author brad.wu
 */
public interface IRelatedBranch extends IBranch {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IBranch#getParentBranch()
	 */
	IRelatedBranch getParentBranch();

	/**
	 * set parent branch
	 * 
	 * @param parentBranch
	 */
	void setParentBranch(IRelatedBranch parentBranch);

	/**
	 * get artificial person(法人代表)
	 * 
	 * @return
	 */
	IRelatedEmployee getArtificialPerson();

	/**
	 * 
	 * @param artificialPerson
	 */
	void setArtificialPerson(IRelatedEmployee artificialPerson);
}
