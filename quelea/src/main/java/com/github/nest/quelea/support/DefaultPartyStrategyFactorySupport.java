/**
 * 
 */
package com.github.nest.quelea.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.nest.quelea.QueleaRuntimeException;
import com.github.nest.quelea.model.Individual;
import com.github.nest.quelea.model.Organization;
import com.github.nest.quelea.model.Party;

/**
 * default party name strategy factory support
 * 
 * @author brad.wu
 */
@SuppressWarnings("rawtypes")
@Component("partyStrategyFactory")
public class DefaultPartyStrategyFactorySupport implements IPartyStrategyFactory {
	@Autowired
	@Qualifier("individualStrategy")
	private IPartyStrategy individualStrategy;

	@Autowired
	@Qualifier("organizationStrategy")
	private IPartyStrategy organizationStrategy;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.support.IPartyStrategyFactory#getPartyStrategy(com.github.nest.quelea.model.Party)
	 */
	@Override
	public IPartyStrategy getPartyStrategy(Party party) {
		if (party instanceof Individual) {
			return individualStrategy;
		} else if (party instanceof Organization) {
			return organizationStrategy;
		} else {
			throw new QueleaRuntimeException(QueleaRuntimeException.UNSUPPORTED_PARTY_TYPE, "Unsupported party["
					+ party.getClass().getName() + "] when get party strategy.");
		}
	}
}
