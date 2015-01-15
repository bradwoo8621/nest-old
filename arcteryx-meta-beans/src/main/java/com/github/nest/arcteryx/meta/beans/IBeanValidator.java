/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

/**
 * Bean validator<br>
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
	List<IConstraintViolation> validate(Object resource);

	/**
	 * validate resource under given profile
	 * 
	 * @param resource
	 * @param profiles
	 * @return
	 */
	List<IConstraintViolation> validate(Object resource, String... profiles);

	/**
	 * validate resource under give group. for JSR303.
	 * 
	 * @param resource
	 * @param groups
	 * @return
	 */
	List<IConstraintViolation> validate(Object resource, Class<?>... groups);
}
