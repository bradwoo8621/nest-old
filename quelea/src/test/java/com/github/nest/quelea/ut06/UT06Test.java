package com.github.nest.quelea.ut06;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.quelea.model.Address;
import com.github.nest.quelea.model.Bank;
import com.github.nest.quelea.model.IndividualAgent;
import com.github.nest.quelea.model.Party;
import com.github.nest.quelea.repository.PartyRepository;
import com.github.nest.quelea.repository.PartyRoleRepository;

public class UT06Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		Logger.getLogger("org.hibernate.type").setLevel(Level.TRACE);

		System.setProperty("spring.profiles.active", "test");
		ApplicationContext context = Context.createApplicationContextByClassPath("ut06",
				"/com/github/nest/quelea/ut06/Context.xml");
		PartyRepository pRep = context.getBean(PartyRepository.class);
		PartyRoleRepository prRep = context.getBean(PartyRoleRepository.class);

		PlatformTransactionManager tm = context.getBean(PlatformTransactionManager.class);
		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = tm.getTransaction(paramTransactionDefinition);

		Party individual = pRep.findOne(1l);
		Party organization = pRep.findOne(2l);
		Address address = organization.getAddresses().get(0);

		Bank bank = new Bank();
		bank.setRoleCode("B00001");
		bank.setParty(organization);
		bank.setDefaultAddress(address);
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);
		bank.setAddresses(addresses);

		IndividualAgent agent = new IndividualAgent();
		agent.setRoleCode("AGT00001");
		agent.setParty(individual);

		try {
			prRep.save(bank);
			prRep.save(agent);
			tm.commit(status);
		} catch (Exception e) {
			tm.rollback(status);
			e.printStackTrace();
		}

		bank = (Bank) prRep.findByRoleCode("B00001");
		assertEquals(Long.valueOf(2), bank.getParty().getPartyId());
	}
}
