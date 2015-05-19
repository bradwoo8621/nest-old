package com.github.nest.quelea.ut01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.quelea.internal.model.Individual;

public class UT01Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createApplicationContextByClassPath("ut02",
				"/com/github/nest/quelea/ut01/Context.xml");

		Individual party = context.getBean("individual", Individual.class);
		party.setFirstName("John");
		party.setLastName("Doe");
		assertEquals("John Doe", party.getPartyName());

		party = context.getBean("individual", Individual.class);
		party.setFirstName("三");
		party.setLastName("張");
		assertEquals("張三", party.getPartyName());
	}
}
