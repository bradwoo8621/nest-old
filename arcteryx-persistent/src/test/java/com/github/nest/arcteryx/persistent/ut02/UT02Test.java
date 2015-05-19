/**
 * 
 */
package com.github.nest.arcteryx.persistent.ut02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;

/**
 * @author brad.wu
 */
public class UT02Test extends EnableLogger {
	@Test
	public void test() {
		System.setProperty("spring.profiles.active", "test");
		ApplicationContext context = Context.createApplicationContextByClassPath("ut02",
				"/com/github/nest/arcteryx/persistent/ut02/context.xml");

		UtTable ut = new UtTable();
		ut.setName("ut name");

		UtTableRepository rep = context.getBean(UtTableRepository.class);
		assertNotNull(rep);

		PlatformTransactionManager tm = context.getBean(PlatformTransactionManager.class);
		DefaultTransactionDefinition paramTransactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus status = tm.getTransaction(paramTransactionDefinition);
		try {
			rep.save(ut);
			tm.commit(status);
		} catch (Exception e) {
			tm.rollback(status);
		}

		Iterable<UtTable> all = rep.findAll();
		ut = all.iterator().next();
		assertNotNull(ut);
		assertEquals("ut name", ut.getName());
		assertEquals(Long.valueOf(1), ut.getCreatedBy());
		assertEquals(Long.valueOf(1), ut.getLastModifiedBy());
	}
}
