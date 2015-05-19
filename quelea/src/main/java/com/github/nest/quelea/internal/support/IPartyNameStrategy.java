/**
 * 
 */
package com.github.nest.quelea.internal.support;

import com.github.nest.quelea.model.IParty;

/**
 * party name strategy
 * 
 * @author brad.wu
 */
public interface IPartyNameStrategy<P extends IParty> {
	/**
	 * get party name of given party
	 * 
	 * @param party
	 * @return
	 */
	String getPartyName(P party);
}
