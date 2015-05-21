package com.github.nest.quelea.ut06;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.quelea.model.Bank;
import com.github.nest.quelea.repository.PartyRoleRepository;

public class UT06Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		// Logger.getLogger("org.hibernate.type").setLevel(Level.TRACE);

		System.setProperty("spring.profiles.active", "test");
		ApplicationContext context = Context.createApplicationContextByClassPath("ut06",
				"/com/github/nest/quelea/ut06/Context.xml");

		PartyRoleRepository rep = context.getBean(PartyRoleRepository.class);

		Bank bank = new Bank();
		bank.setRoleCode("B00001");

		PlatformTransactionManager tm = context.getBean(PlatformTransactionManager.class);
		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = tm.getTransaction(paramTransactionDefinition);
		try {
			rep.save(bank);
			tm.commit(status);
		} catch (Exception e) {
			tm.rollback(status);
			e.printStackTrace();
		}
	}
}
