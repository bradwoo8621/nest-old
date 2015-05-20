/**
 * 
 */
package com.github.nest.quelea.ut03;

import com.github.nest.quelea.model.Party;
import com.github.nest.quelea.support.IPartyStrategy;
import com.github.nest.quelea.support.IPartyStrategyFactory;

/**
 * default party name strategy factory support
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public class CustomPartyStrategyFactorySupport implements IPartyStrategyFactory {
	@Override
	public IPartyStrategy getPartyStrategy(Party party) {
		return new IPartyStrategy() {
			@Override
			public String getPartyName(Party party) {
				return "Hello, world!";
			}
		};
	}
}
