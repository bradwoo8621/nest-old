/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author brad.wu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@net.sf.oval.configuration.annotation.Constraint(checkWith = UpperCaseCheck.class)
public @interface UpperCase {
	/**
	 * message to be used for the ConstraintsViolatedException
	 *
	 * @see ConstraintsViolatedException
	 */
	String message() default "UpperCase.violated";
}
