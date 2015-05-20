/**
 * 
 */
package com.github.nest.quelea.support;

import com.github.nest.quelea.model.Party;

/**
 * party name strategy factory
 * 
 * @author brad.wu
 */
public interface IPartyStrategyFactory {
	/**
	 * get party name strategy by given party
	 * 
	 * @param party
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	IPartyStrategy getPartyStrategy(Party party);
}
