/**
 * 
 */
package com.github.nest.quelea.internal.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.nest.quelea.QueleaRuntimeException;
import com.github.nest.quelea.model.IIndividual;
import com.github.nest.quelea.model.IOrganization;
import com.github.nest.quelea.model.IParty;

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
	 * @see com.github.nest.quelea.internal.support.IPartyNameStrategyFactory#getPartyNameStrategy(com.github.nest.quelea.model.IParty)
	 */
	@Override
	public IPartyNameStrategy getPartyNameStrategy(IParty party) {
		if (party instanceof IIndividual) {
			return individualPartyNameStrategy;
		} else if (party instanceof IOrganization) {
			return organizationPartyNameStrategy;
		} else {
			throw new QueleaRuntimeException(QueleaRuntimeException.UNSUPPORTED_PARTY_TYPE, "Unsupported party["
					+ party.getClass().getName() + "] when get party name strategy.");
		}
	}
}
