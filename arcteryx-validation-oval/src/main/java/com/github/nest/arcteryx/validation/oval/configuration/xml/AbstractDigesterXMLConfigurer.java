/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import net.sf.oval.configuration.Configurer;
import net.sf.oval.configuration.pojo.POJOConfigurer;
import net.sf.oval.configuration.pojo.elements.ClassConfiguration;
import net.sf.oval.configuration.pojo.elements.ConstraintSetConfiguration;
import net.sf.oval.exception.InvalidConfigurationException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.binder.RulesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * digester xml configurer
 * 
 * @author brad.wu
 */
public abstract class AbstractDigesterXMLConfigurer implements Configurer {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private DigesterLoader loader = null;
	private POJOConfigurer configurer = null;

	static {
		ConvertUtils.register(new OvalClassConverter(), Class.class);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.configuration.Configurer#getClassConfiguration(java.lang.Class)
	 */
	@Override
	public ClassConfiguration getClassConfiguration(Class<?> clazz) throws InvalidConfigurationException {
		return this.configurer.getClassConfiguration(clazz);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see net.sf.oval.configuration.Configurer#getConstraintSetConfiguration(java.lang.String)
	 */
	@Override
	public ConstraintSetConfiguration getConstraintSetConfiguration(String constraintSetId)
			throws InvalidConfigurationException {
		return this.configurer.getConstraintSetConfiguration(constraintSetId);
	}

	/**
	 * get configurer
	 * 
	 * @return
	 */
	public Configurer getConfigurer() {
		return this.configurer;
	}

	/**
	 * read configuration from given file
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void fromXML(File file) throws IOException, SAXException {
		initializeDigesterLoader();
		this.configurer = getDigester().parse(file);
	}

	/**
	 * read configuration from given input stream
	 * 
	 * @param stream
	 * @throws IOException
	 * @throws SAXException
	 */
	public void fromXML(InputStream stream) throws IOException, SAXException {
		initializeDigesterLoader();
		this.configurer = getDigester().parse(stream);
	}

	/**
	 * read configuration from given reader
	 * 
	 * @param reader
	 * @throws IOException
	 * @throws SAXException
	 */
	public void fromXML(Reader reader) throws IOException, SAXException {
		initializeDigesterLoader();
		this.configurer = getDigester().parse(reader);
	}

	/**
	 * read configuration from given xml string
	 * 
	 * @param xml
	 * @throws IOException
	 * @throws SAXException
	 */
	public void fromXML(String xml) throws IOException, SAXException {
		initializeDigesterLoader();
		this.configurer = getDigester().parse(xml);
	}

	/**
	 * read configuration from given input source
	 * 
	 * @param source
	 * @throws IOException
	 * @throws SAXException
	 */
	public void fromXML(InputSource source) throws IOException, SAXException {
		initializeDigesterLoader();
		this.configurer = getDigester().parse(source);
	}

	/**
	 * read configuration from given URL
	 * 
	 * @param url
	 * @throws IOException
	 * @throws SAXException
	 */
	public void fromXML(final URL url) throws IOException, SAXException {
		initializeDigesterLoader();
		this.configurer = getDigester().parse(url);
	}

	/**
	 * initialize digester loader
	 */
	public void initializeDigesterLoader() {
		this.loader = DigesterLoader.newLoader(createRulesModule());
	}

	/**
	 * create rules module
	 * 
	 * @return
	 */
	protected abstract RulesModule createRulesModule();

	/**
	 * get digester
	 * 
	 * @return
	 */
	protected Digester getDigester() {
		return this.loader.newDigester();
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}
}
