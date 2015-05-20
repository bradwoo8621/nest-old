package com.github.nest.quelea.ut02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.quelea.internal.model.Individual;
import com.github.nest.quelea.internal.model.Party;
import com.github.nest.quelea.internal.repository.PartyRepository;
import com.github.nest.quelea.internal.support.IPartyNameStrategyFactory;

public class UT02Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		System.setProperty("spring.profiles.active", "test");
		ApplicationContext context = Context.createApplicationContextByClassPath("ut02",
				"/com/github/nest/quelea/ut02/Context.xml");
		IPartyNameStrategyFactory factory = context.getBean(IPartyNameStrategyFactory.class);

		Individual individual = new Individual();
		individual.setFirstName("John");
		individual.setLastName("Doe");
		individual.setIdNumber("X000001");
		individual.setPartyName(factory.getPartyNameStrategy(individual).getPartyName(individual));

		PartyRepository rep = context.getBean(PartyRepository.class);
		assertNotNull(rep);

		PlatformTransactionManager tm = context.getBean(PlatformTransactionManager.class);
		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = tm.getTransaction(paramTransactionDefinition);
		try {
			rep.save(individual);
			tm.commit(status);
		} catch (Exception e) {
			tm.rollback(status);
			e.printStackTrace();
		}

		Long partyId = individual.getPartyId();

		Party party = rep.findOne(partyId);
		assertEquals(Individual.class, party.getClass());
		individual = (Individual) party;
		assertEquals("John", individual.getFirstName());
		assertEquals("Doe", individual.getLastName());
		assertEquals("X000001", individual.getIdNumber());
		assertEquals("John Doe", individual.getPartyName());
	}
}
