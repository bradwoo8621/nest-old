/**
 * 
 */
package com.github.nest.quelea.ut04;

import org.springframework.stereotype.Component;

import com.github.nest.quelea.internal.support.IPartyNameStrategy;
import com.github.nest.quelea.internal.support.IPartyNameStrategyFactory;
import com.github.nest.quelea.model.IParty;

/**
 * default party name strategy factory support
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
@Component("partyNameStrategyFactory")
public class CustomPartyNameStrategyFactorySupport implements IPartyNameStrategyFactory {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.internal.support.IPartyNameStrategyFactory#getPartyNameStrategy(com.github.nest.quelea.model.IParty)
	 */
	@Override
	public IPartyNameStrategy getPartyNameStrategy(IParty party) {
		return new IPartyNameStrategy() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see com.github.nest.quelea.internal.support.IPartyNameStrategy#getPartyName(com.github.nest.quelea.model.IParty)
			 */
			@Override
			public String getPartyName(IParty party) {
				return "Hello, world!";
			}
		};
	}
}
