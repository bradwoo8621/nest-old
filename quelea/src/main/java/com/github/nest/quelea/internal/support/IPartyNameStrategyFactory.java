/**
 * 
 */
package com.github.nest.quelea.internal.support;

import com.github.nest.quelea.model.IParty;

/**
 * party name strategy factory
 * 
 * @author brad.wu
 */
public interface IPartyNameStrategyFactory {
	/**
	 * get party name strategy by given party
	 * 
	 * @param party
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	IPartyNameStrategy getPartyNameStrategy(IParty party);
}
