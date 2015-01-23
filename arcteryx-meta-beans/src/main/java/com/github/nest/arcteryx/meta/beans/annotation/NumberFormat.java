/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.ConstraintApplyTo;
import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.constraints.NumberFormatConstraint;

/**
 * number format
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(constraintClass = NumberFormatConstraint.class)
public @interface NumberFormat {
	/**
	 * name of constraint
	 * 
	 * @return
	 */
	String name() default "";

	/**
	 * message template
	 * 
	 * @return
	 */
	String messageTemplate() default "";

	/**
	 * error code
	 * 
	 * @return
	 */
	String errorCode() default "";

	/**
	 * profiles
	 * 
	 * @return
	 */
	String[] profiles() default {};

	/**
	 * severity
	 * 
	 * @return
	 */
	ConstraintSeverity severity();

	/**
	 * when
	 * 
	 * @return
	 */
	String when() default "";

	/**
	 * applies to
	 * 
	 * @return
	 */
	ConstraintApplyTo appliesTo();

	/**
	 * target
	 * 
	 * @return
	 */
	String target() default "";

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.FIELD, ElementType.METHOD })
	@Constraints
	public @interface List {
		NumberFormat[] values();
	}

	/**
	 * min integer digits
	 * 
	 * @return
	 */
	int minIntegerDigits() default 0;

	/**
	 * min fraction digits
	 * 
	 * @return
	 */
	int minFractionDigits() default 0;

	/**
	 * max integer digits
	 * 
	 * @return
	 */
	int maxIntegerDigits() default Integer.MAX_VALUE;

	/**
	 * max fraction digits
	 * 
	 * @return
	 */
	int maxFractionDigits() default Integer.MAX_VALUE;
}
