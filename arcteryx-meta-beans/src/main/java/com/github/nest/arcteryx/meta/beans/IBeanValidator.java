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
	<T> T validate(Object resource);

	/**
	 * validate resource under given profile
	 * 
	 * @param resource
	 * @param profile
	 * @return
	 */
	<T> T validate(Object resource, String... profile);

	/**
	 * validate resource under give group. for JSR303.
	 * 
	 * @param resource
	 * @param profile
	 * @return
	 */
	<T> T validate(Object resource, Class<?>... group);
}
