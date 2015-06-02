package com.github.nest.arcteryx.data.codes.ut01;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;

public class UT01Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createContextByClassPath("ut01",
				"/com/github/nest/arcteryx/data/codes/ut01/CodeTableA.xml");
		ICodeTableRegistry registry = context.getBean("CodeTableRegistry", ICodeTableRegistry.class);

		CodeTableA codeTable = registry.getCodeTable("CodeTableA");
		assertTrue(codeTable.contains("CodeA"));
		assertTrue(codeTable.contains("CodeB"));
		assertTrue(codeTable.contains("CodeC"));
		assertFalse(codeTable.contains("CodeD"));

		registry.deregister("CodeTableA");
		codeTable = registry.getCodeTable("CodeTableA");
		assertNull(codeTable);
	}
}
