package com.github.nest.arcteryx.data.codes.ut02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;
import com.github.nest.arcteryx.data.internal.codes.utils.js.JSONGenerator;

public class UT02Test extends EnableLogger {
	/**
	 * test json generator
	 */
	@Test
	public void test() {
		ApplicationContext context = Context.createApplicationContextByClassPath("ut02",
				"/com/github/nest/arcteryx/data/codes/ut02/CodeTableA.xml");
		ICodeTableRegistry registry = context.getBean("CodeTableRegistry", ICodeTableRegistry.class);

		System.out.println(JSONGenerator.generate(registry));
	}
}
