package com.github.nest.arcteryx.data.codes.ut03;

import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;

import com.github.nest.arcteryx.common.ut.EnableLogger;
import com.github.nest.arcteryx.context.Context;

public class UT03Test extends EnableLogger {
	/**
	 * for testing the code table file not found when using
	 * DefaultCodeTableContentProvider
	 */
	@Test(expected = BeanCreationException.class)
	public void test() {
		Context.createContextByClassPath("ut03", "/com/github/nest/arcteryx/data/codes/ut03/CodeTableA.xml");
	}
}
