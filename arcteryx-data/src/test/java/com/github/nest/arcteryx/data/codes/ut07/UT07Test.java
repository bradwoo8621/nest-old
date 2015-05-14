package com.github.nest.arcteryx.data.codes.ut07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;
import com.github.nest.arcteryx.data.internal.codes.HierarchyCodeItemFilter;

public class UT07Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createApplicationContextByClassPath("ut07",
				"/com/github/nest/arcteryx/data/codes/ut07/CodeTableA.xml");
		ICodeTableRegistry registry = context.getBean("CodeTableRegistry", ICodeTableRegistry.class);

		CodeTableA codeTable = registry.getCodeTable("CodeTableA");
		assertTrue(codeTable.contains("CodeA"));
		assertTrue(codeTable.contains("CodeB"));
		assertTrue(codeTable.contains("CodeC"));
		assertFalse(codeTable.contains("CodeD"));

		HierarchyCodeItemFilter filter = new HierarchyCodeItemFilter("X");
		Collection<ICodeItem> items = codeTable.filter(filter);
		assertEquals(2, items.size());
		for (ICodeItem item : items) {
			assertTrue("CodeA, CodeB".indexOf(item.getCode()) != -1);
		}

		filter = new HierarchyCodeItemFilter("Y");
		items = codeTable.filter(filter);
		assertEquals(1, items.size());
		assertEquals("CodeC", items.iterator().next().getCode());

		registry.deregister("CodeTableA");
		codeTable = registry.getCodeTable("CodeTableA");
		assertNull(codeTable);
	}
}
