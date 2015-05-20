/**
 * 
 */
package com.github.nest.arcteryx.common.ut;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;

/**
 * extend this to enable the log4j logger to INFO level. <br>
 * import slf4j-log4j12-xxx.jar is necessary.
 * 
 * @author brad.wu
 */
public class EnableLogger {
	private static boolean initialized = false;

	@BeforeClass
	public static void enable() {
		if (!initialized) {
			BasicConfigurator.configure();
			Logger.getRootLogger().setLevel(Level.INFO);
			initialized = true;
		}
	}
}
