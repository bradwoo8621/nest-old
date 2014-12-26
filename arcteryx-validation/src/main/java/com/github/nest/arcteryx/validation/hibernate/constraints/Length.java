/**
 * 
 */
package com.github.nest.arcteryx.validation.hibernate.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.nest.arcteryx.validation.hibernate.validators.LengthValidator;

/**
 * Validate that the string is between min and max included.
 * 
 * @author brad.wu
 */
@Documented
@Constraint(validatedBy = { LengthValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface Length {
	/**
	 * minimum length
	 * 
	 * @return
	 */
	int min() default 0;

	/**
	 * maximum length
	 * 
	 * @return
	 */
	int max() default Integer.MAX_VALUE;

	/**
	 * message when failed
	 * 
	 * @return
	 */
	String message() default "{com.github.nest.arcteryx.validation.hibernate.constraints.Length.message}";

	/**
	 * groups when validate
	 * 
	 * @return
	 */
	Class<?>[] groups() default {};

	/**
	 * payload will be returned to violation
	 * 
	 * @return
	 */
	Class<? extends Payload>[] payload() default {};

	/**
	 * target from value to validate,<br>
	 * <ul>
	 * <li>a.b.c</li>
	 * <li>JXPath format</li>
	 * </ul>
	 * 
	 * @return
	 */
	String target() default "";

	/**
	 * el expression, only the expression result is true, will run the
	 * validation. otherwise, pass the validation.
	 * 
	 * @return
	 */
	String when() default "";

	/**
	 * Defines several {@code @Length} annotations on the same element.
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		Length[] values();
	}
}
