/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.constraint.CheckWithCheck.SimpleCheck;
import net.sf.oval.exception.ReflectionException;

/**
 * @author brad.wu
 *
 */
public class SimpleCheckWrapper {
	private String className = null;

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * set to check
	 * 
	 * @param check
	 * @throws ReflectionException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public void setToCheck(CheckWithCheck check) throws ReflectionException, IllegalArgumentException,
			ClassNotFoundException {
		check.setSimpleCheck((Class<? extends SimpleCheck>) Class.forName(getClassName()));
	}
}
