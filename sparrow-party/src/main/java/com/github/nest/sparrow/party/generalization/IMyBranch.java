/**
 * 
 */
package com.github.nest.sparrow.party.generalization;

import com.github.nest.sparrow.party.IPartyRole;

/**
 * my branch
 * 
 * @author brad.wu
 */
public interface IMyBranch extends IBranch, IPartyRole {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.generalization.IBranch#getParentBranch()
	 */
	IMyBranch getParentBranch();
}
