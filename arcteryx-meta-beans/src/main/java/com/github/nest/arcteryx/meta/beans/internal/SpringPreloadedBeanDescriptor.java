/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal;

/**
 * preloaded bean descriptor which will describe properties of spring beans
 * 
 * @author brad.wu
 */
public class SpringPreloadedBeanDescriptor extends PreloadedBeanDescriptor {
	private static final long serialVersionUID = 1341925180862025155L;

	private String springContextId = null;
	private String springBeanId = null;

	/**
	 * @return the springBeanId
	 */
	public String getSpringBeanId() {
		return springBeanId;
	}

	/**
	 * @param springBeanId
	 *            the springBeanId to set
	 */
	public void setSpringBeanId(String springBeanId) {
		this.springBeanId = springBeanId;
	}

	/**
	 * @return the springContextId
	 */
	public String getSpringContextId() {
		return springContextId;
	}

	/**
	 * @param springContextId
	 *            the springContextId to set
	 */
	public void setSpringContextId(String springContextId) {
		this.springContextId = springContextId;
	}
}
