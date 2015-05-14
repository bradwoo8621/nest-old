package com.github.nest.arcteryx.data.codes.ut05;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;

public class UT05Test extends EnableLogger {
	/**
	 * test code table B replace A
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createApplicationContextByClassPath("ut05",
				"/com/github/nest/arcteryx/data/codes/ut05/CodeTableA.xml");
		ICodeTableRegistry registry = context.getBean("CodeTableRegistry", ICodeTableRegistry.class);

		CodeTableA codeTable = registry.getCodeTable("CodeTableA");
		assertTrue(codeTable.contains("CodeA"));
		assertTrue(codeTable.contains("CodeB"));

		// differences
		assertFalse(codeTable.contains("CodeC"));
		assertTrue(codeTable.contains("CodeD"));

		registry.deregister("CodeTableA");
		codeTable = registry.getCodeTable("CodeTableA");
		assertNull(codeTable);
	}
}
