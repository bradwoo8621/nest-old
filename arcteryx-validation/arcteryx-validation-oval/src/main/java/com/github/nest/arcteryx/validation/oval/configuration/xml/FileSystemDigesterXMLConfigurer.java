package com.github.nest.arcteryx.validation.oval.configuration.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.digester3.binder.RulesModule;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;

/**
 * class path digester xml configurer.
 * 
 * @author brad.wu
 */
public class FileSystemDigesterXMLConfigurer extends AbstractDigesterXMLConfigurer {
	private String fileName = null;

	/**
	 * get file name
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * set file name
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.oval.configuration.xml.AbstractDigesterXMLConfigurer#createRulesModule()
	 */
	@Override
	protected RulesModule createRulesModule() {
		final Reader reader;
		try {
			reader = new FileReader(getFileName());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Failed to build file reader[" + getFileName() + "].", e);
		}
		return new FromXmlRulesModule() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see org.apache.commons.digester3.xmlrules.FromXmlRulesModule#loadRules()
			 */
			@Override
			protected void loadRules() {
				this.loadXMLRules(reader);
			}

			/**
			 * (non-Javadoc)
			 * 
			 * @see org.apache.commons.digester3.xmlrules.FromXmlRulesModule#configure()
			 */
			@Override
			protected void configure() {
				super.configure();
				try {
					reader.close();
				} catch (IOException e) {
					getLogger().error("Failed to close input stream[" + getFileName() + "].", e);
				}
			}
		};
	}
}
