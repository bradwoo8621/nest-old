/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints;

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
import javax.validation.constraints.NotNull;

/**
 * number format
 * 
 * @author brad.wu
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { NumberFormatValidator.class })
public @interface NumberFormat {
	String message() default "{com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.constraints.NumberFormat.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link NotNull} annotations on the same element.
	 *
	 * @see javax.validation.constraints.NotNull
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		NumberFormat[] value();
	}

	/**
	 * min integer digits, default 0
	 * 
	 * @return
	 */
	int minIntegerDigits() default 0;

	/**
	 * min fraction digits, default 0
	 * 
	 * @return
	 */
	int minFractionDigits() default 0;

	/**
	 * max integer digits, default {@linkplain Integer#MAX_VALUE}
	 * 
	 * @return
	 */
	int maxIntegerDigits() default Integer.MAX_VALUE;

	/**
	 * max fraction digits, default {@linkplain Integer#MAX_VALUE}
	 * 
	 * @return
	 */
	int maxFractionDigits() default Integer.MAX_VALUE;

}
