/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.restlet.Context;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * component
 * 
 * @author brad.wu
 * @author yi.feng
 */
public class Component extends org.restlet.Component implements InitializingBean, ApplicationContextAware {
	public static final String API = "api";

	private Logger logger = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext;
	private List<ApplicationResourceScanner> scanners = null;
	private String apiContentPath = null;
	private String uriPatternPrefix = "";

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * get sfl4j logger
	 * 
	 * @return
	 */
	public Logger getSlfLogger() {
		return this.logger;
	}

	/**
	 * @return the scanners
	 */
	public List<ApplicationResourceScanner> getScanners() {
		return scanners;
	}

	/**
	 * @param scanners
	 *            the scanners to set
	 */
	public void setScanners(List<ApplicationResourceScanner> scanners) {
		this.scanners = scanners;
	}

	/**
	 * create jaxrs application
	 * 
	 * @return
	 */
	protected JaxRsApplication createJaxRsApplication() {
		JaxRsApplication jaxRsApplication = this.getApplicationContext().getBean(JaxRsApplication.class);
		Context context = this.getContext().createChildContext();
		jaxRsApplication.setContext(context);
		return jaxRsApplication;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initApplications();
	}

	/**
	 * init applications
	 */
	protected void initApplications() {
		if (this.getScanners() != null) {
			for (ApplicationResourceScanner scanner : this.getScanners()) {
				JaxRsApplication application = this.createJaxRsApplication();
				application.add(scanner);
				String uriPattern = this.getUriPatternPrefix() + "/" + scanner.getModule() + "/" + scanner.getVersion();
				this.getDefaultHost().attach(uriPattern, application);
				this.getSlfLogger().trace("Application[module={}, version={}] attached.", scanner.getModule(),
						scanner.getVersion());
			}
		} else {
			this.getSlfLogger().warn("No scanner registered in component.");
		}
	}

	/**
	 * 
	 * @param entry
	 * @param application
	 */
	protected void attachSwaggerDocumentService(ApplicationResourceScanner application) {
		JaxRSSwaggerRestlet swaggerRestlet = null;
		try {
			swaggerRestlet = this.getApplicationContext().getBean(JaxRSSwaggerRestlet.class);
		} catch (NoSuchBeanDefinitionException e) {
			this.getSlfLogger().info("Swagger not found.", e);
			return;
		}
		swaggerRestlet.setJaxRSApplication(application);
		String uriPattern = this.getUriPatternPrefix() + "/" + getApiContentPath() + "/" + application.getModule()
				+ "/" + application.getVersion();
		this.getDefaultHost().attach(uriPattern, swaggerRestlet);
	}

	/**
	 * @return the apiContentPath
	 */
	public String getApiContentPath() {
		return StringUtils.isBlank(apiContentPath) ? API : this.apiContentPath;
	}

	/**
	 * @param apiContentPath
	 *            the apiContentPath to set
	 */
	public void setApiContentPath(String apiContentPath) {
		this.apiContentPath = apiContentPath;
	}

	/**
	 * get uri pattern prefix, default empty string
	 * 
	 * @return
	 */
	protected String getUriPatternPrefix() {
		return StringUtils.isBlank(this.uriPatternPrefix) ? "" : this.uriPatternPrefix;
	}

	/**
	 * will be set in
	 * {@linkplain JaxRSSpringServlet#getComponent(javax.servlet.http.HttpServletRequest)}
	 * , do not call this method manually.
	 * 
	 * @param uriPatternPrefix
	 *            the uriPatternPrefix to set
	 */
	protected void setUriPatternPrefix(String uriPatternPrefix) {
		this.uriPatternPrefix = uriPatternPrefix;
	}
}
