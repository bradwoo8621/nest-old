/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester3.binder.RulesModule;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;

/**
 * class path digester xml configurer.
 * 
 * @author brad.wu
 */
public class ClassPathDigesterXMLConfigurer extends AbstractDigesterXMLConfigurer {
	public static ClassPathDigesterXMLConfigurer INSTANCE = new ClassPathDigesterXMLConfigurer();

	static {
		INSTANCE.setClassPath("oval-config.xml");
	}

	private String classPath = null;

	/**
	 * get class path
	 * 
	 * @return
	 */
	public String getClassPath() {
		return classPath;
	}

	/**
	 * set class path
	 * 
	 * @param classPath
	 */
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.validation.oval.configuration.xml.AbstractDigesterXMLConfigurer#createRulesModule()
	 */
	@Override
	protected RulesModule createRulesModule() {
		final InputStream is = getClass().getResourceAsStream(getClassPath());
		return new FromXmlRulesModule() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see org.apache.commons.digester3.xmlrules.FromXmlRulesModule#loadRules()
			 */
			@Override
			protected void loadRules() {
				this.loadXMLRules(is);
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
					is.close();
				} catch (IOException e) {
					getLogger().error("Failed to close input stream[" + getClassPath() + "].", e);
				}
			}
		};
	}
}
