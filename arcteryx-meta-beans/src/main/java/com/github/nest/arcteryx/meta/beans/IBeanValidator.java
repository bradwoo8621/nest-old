/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * Bean validator<br>
 * TODO convert the valdation result to pre-defined interface
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
	 * @param profiles
	 * @return
	 */
	<T> T validate(Object resource, String... profiles);

	/**
	 * validate resource under give group. for JSR303.
	 * 
	 * @param resource
	 * @param groups
	 * @return
	 */
	<T> T validate(Object resource, Class<?>... groups);
}
