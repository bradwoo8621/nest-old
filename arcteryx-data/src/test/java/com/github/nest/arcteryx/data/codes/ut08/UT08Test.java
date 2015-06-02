package com.github.nest.arcteryx.data.codes.ut08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableFilter;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;

public class UT08Test extends EnableLogger {
	/**
	 * normal test, code table with a .codetable file
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createContextByClassPath("ut08",
				"/com/github/nest/arcteryx/data/codes/ut08/CodeTableA.xml");
		ICodeTableRegistry registry = context.getBean("CodeTableRegistry", ICodeTableRegistry.class);

		CodeTableA codeTable = registry.getCodeTable("CodeTableA");
		assertTrue(codeTable.contains("CodeA"));
		assertTrue(codeTable.contains("CodeB"));
		assertTrue(codeTable.contains("CodeC"));
		assertFalse(codeTable.contains("CodeD"));

		Collection<ICodeItem> items = codeTable.getItems(new ICodeTableFilter() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see com.github.nest.arcteryx.data.codes.ICodeTableFilter#filter(com.github.nest.arcteryx.data.codes.ICodeTable)
			 */
			@Override
			public List<ICodeItem> filter(ICodeTable codeTable) {
				List<ICodeItem> items = new ArrayList<ICodeItem>();
				for (ICodeItem item : codeTable.getItems()) {
					if ("CodeA, CodeB".indexOf(item.getCode()) != -1) {
						items.add(item);
					}
				}
				return items;
			}
		});
		assertEquals(2, items.size());
		for (ICodeItem item : items) {
			assertTrue("CodeA, CodeB".indexOf(item.getCode()) != -1);
		}

		registry.deregister("CodeTableA");
		codeTable = registry.getCodeTable("CodeTableA");
		assertNull(codeTable);
	}
}
