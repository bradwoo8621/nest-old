package com.github.nest.quelea.ut01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.quelea.model.Individual;
import com.github.nest.quelea.support.IPartyNameStrategyFactory;

public class UT01Test extends EnableLogger {
	/**
	 * test partyNameStrategyFactory
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		ApplicationContext context = Context.createApplicationContextByClassPath("ut01",
				"/com/github/nest/quelea/ut01/Context.xml");
		IPartyNameStrategyFactory factory = context.getBean(IPartyNameStrategyFactory.class);

		Individual party = new Individual();
		party.setFirstName("John");
		party.setLastName("Doe");
		party.setPartyName(factory.getPartyNameStrategy(party).getPartyName(party));
		assertEquals("John Doe", party.getPartyName());

		party = new Individual();
		party.setFirstName("三");
		party.setLastName("張");
		party.setPartyName(factory.getPartyNameStrategy(party).getPartyName(party));
		assertEquals("張三", party.getPartyName());
	}
}
