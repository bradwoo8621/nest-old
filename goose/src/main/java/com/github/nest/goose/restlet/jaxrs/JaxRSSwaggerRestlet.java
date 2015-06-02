/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import javax.ws.rs.core.Application;

import org.restlet.Restlet;

/**
 * JaxRS swagger restlet
 * 
 * @author brad.wu
 */
public abstract class JaxRSSwaggerRestlet extends Restlet {
	private Application jaxRSApplication;

	/**
	 * @return the jaxRSApplication
	 */
	public Application getJaxRSApplication() {
		return jaxRSApplication;
	}

	/**
	 * @param jaxRSApplication
	 *            the jaxRSApplication to set
	 */
	public void setJaxRSApplication(Application jaxRSApplication) {
		this.jaxRSApplication = jaxRSApplication;
	}
}
