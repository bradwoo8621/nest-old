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
import com.github.nest.arcteryx.meta.beans.constraints.DateRangeConstraint;

/**
 * date range
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(constraintClass = DateRangeConstraint.class)
public @interface DateRange {
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
	ConstraintSeverity severity() default ConstraintSeverity.ERROR;

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
	ConstraintApplyTo appliesTo() default ConstraintApplyTo.VALUES;

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
		DateRange[] values();
	}

	/**
	 * from
	 * 
	 * @return
	 */
	String from() default "";

	/**
	 * exclude from
	 * 
	 * @return
	 */
	boolean excludeFrom() default false;

	/**
	 * to
	 * 
	 * @return
	 */
	String to() default "";

	/**
	 * exclude to
	 * 
	 * @return
	 */
	boolean excludeTo() default false;

	/**
	 * format
	 * 
	 * @return
	 */
	String format() default "yyyyMMdd";

	/**
	 * tolerance
	 * 
	 * @return
	 */
	int tolerance() default 0;
}
