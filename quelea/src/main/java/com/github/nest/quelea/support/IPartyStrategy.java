/**
 * 
 */
package com.github.nest.quelea.support;

import com.github.nest.quelea.model.Party;

/**
 * party name strategy
 * 
 * @author brad.wu
 */
public interface IPartyStrategy<P extends Party> {
	/**
	 * get party name of given party
	 * 
	 * @param party
	 * @return
	 */
	String getPartyName(P party);
}
