/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.xml;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;

import com.github.nest.arcteryx.validation.oval.configuration.xml.ClassPathDigesterXMLConfigurer;

/**
 * @author brad.wu
 */
public class TestClassPathConfiguration {
	@Test
	public void test() throws Exception {
		ClassPathDigesterXMLConfigurer configurer = ClassPathDigesterXMLConfigurer.INSTANCE;
		InputStream is = getClass().getResourceAsStream("test.xml");
		configurer.fromXML(is);
		is.close();

		assertNotNull(configurer.getConfigurer());
	}
}
