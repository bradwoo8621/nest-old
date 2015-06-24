/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * resource scanner
 * 
 * @author brad.wu
 * @author yi.feng
 */
public class ApplicationResourceScanner extends Application implements ApplicationContextAware {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext;
	private String version = null;
	private String module = null;

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

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
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		String[] names = this.getApplicationContext().getBeanNamesForAnnotation(JaxRSResource.class);
		for (String name : names) {
			Class<?> type = this.getApplicationContext().getType(name);
			if (AopUtils.isAopProxy(type)) {
				this.getLogger().warn("Bean [{}] is a proxy bean.", type.getClass().getName());
			}

			JaxRSResource resource = type.getAnnotation(JaxRSResource.class);
			if (StringUtils.equals(resource.module(), this.getModule())
					&& StringUtils.equals(resource.version(), this.getVersion())) {
				// matched
				classes.add(type);
			} else {
				this.getLogger().trace(
						"{}[module={}, version={}] doesn't match the scanner[module={}, version={}], skipped.",
						type.getName(), resource.module(), resource.version(), this.getModule(), this.getVersion());
			}
		}

		if (classes.isEmpty()) {
			this.getLogger().warn("Application scanner[module={}, version={}] doesn't find any resource.",
					this.getModule(), this.getVersion());
		}

		// add providers
		String[] providerNames = this.getApplicationContext().getBeanNamesForAnnotation(Provider.class);
		for (String providerName : providerNames) {
			Class<?> type = this.getApplicationContext().getType(providerName);
			classes.add(type);
		}
		return classes;
	}

	/**
	 * @return the logger
	 */
	public Logger getLogger() {
		return this.logger;
	}
}
