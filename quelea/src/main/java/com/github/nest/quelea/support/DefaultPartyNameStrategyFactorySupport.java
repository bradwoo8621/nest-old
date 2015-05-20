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
@Component("partyNameStrategyFactory")
public class DefaultPartyNameStrategyFactorySupport implements IPartyNameStrategyFactory {
	@Autowired
	@Qualifier("individualPartyNameStrategy")
	private IPartyNameStrategy individualPartyNameStrategy;

	@Autowired
	@Qualifier("organizationPartyNameStrategy")
	private IPartyNameStrategy organizationPartyNameStrategy;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.support.IPartyNameStrategyFactory#getPartyNameStrategy(com.github.nest.quelea.model.Party)
	 */
	@Override
	public IPartyNameStrategy getPartyNameStrategy(Party party) {
		if (party instanceof Individual) {
			return individualPartyNameStrategy;
		} else if (party instanceof Organization) {
			return organizationPartyNameStrategy;
		} else {
			throw new QueleaRuntimeException(QueleaRuntimeException.UNSUPPORTED_PARTY_TYPE, "Unsupported party["
					+ party.getClass().getName() + "] when get party name strategy.");
		}
	}
}
