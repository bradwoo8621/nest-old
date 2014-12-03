/**
 * 
 */
package com.github.nest.arcteryx.logging.log4j12;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brad.wu
 */
public class TestLogger {
	@Test
	public void testLogger() {
		Logger logger = LoggerFactory.getLogger("test-logger");
		logger.debug("Hello world!");
		logger = LoggerFactory.getLogger(getClass());
		logger.debug("Hello world!");
	}
}
