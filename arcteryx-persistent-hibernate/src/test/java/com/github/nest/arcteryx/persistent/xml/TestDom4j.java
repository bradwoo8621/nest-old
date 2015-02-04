/**
 * 
 */
package com.github.nest.arcteryx.persistent.xml;

import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * @author brad.wu
 *
 */
public class TestDom4j {
	@Test
	public void test() throws IOException {
		Document doc = DocumentHelper.createDocument();
		doc.addDocType("hibernate-mapping", "-//Hibernate/Hibernate Mapping DTD 3.0//EN",
				"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd");

		StringWriter sw = new StringWriter();
		XMLWriter writer = new XMLWriter(sw, OutputFormat.createCompactFormat());
		writer.write(doc);
		System.out.println(sw.toString());
	}
}
