/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * Bean validator
 * 
 * @author brad.wu
 */
public interface IBeanValidator extends IBeanOperator {
	String CODE = "meta.beans.validator";

	/**
	 * validate resource
	 * 
	 * @param resource
	 * @return
	 */
	Object validate(Object resource);
}
