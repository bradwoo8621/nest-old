/**
 * 
 */
package com.github.nest.quelea.ut03;

import com.github.nest.quelea.model.Party;
import com.github.nest.quelea.support.IPartyNameStrategy;
import com.github.nest.quelea.support.IPartyNameStrategyFactory;

/**
 * default party name strategy factory support
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
public class CustomPartyNameStrategyFactorySupport implements IPartyNameStrategyFactory {
	@Override
	public IPartyNameStrategy getPartyNameStrategy(Party party) {
		return new IPartyNameStrategy() {
			@Override
			public String getPartyName(Party party) {
				return "Hello, world!";
			}
		};
	}
}
