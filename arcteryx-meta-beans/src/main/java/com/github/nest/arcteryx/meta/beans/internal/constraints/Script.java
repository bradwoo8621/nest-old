/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.constraints;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyDescriptor;

/**
 * script constraint. script contains two parts, language and script body. eg.
 * groovy:_this.emailAddress != null.
 * 
 * @author brad.wu
 */
public class Script extends AbstractBeanPropertyConstraint {
	private static final long serialVersionUID = -2064084972261552830L;

	private String script = null;

	public Script(IBeanPropertyDescriptor propertyDescriptor) {
		super(propertyDescriptor);
	}

	/**
	 * @return the script
	 */
	protected String getScript() {
		return script;
	}

	/**
	 * @param script
	 *            the script to set
	 */
	protected void setScript(String script) {
		this.script = script;
	}
}
