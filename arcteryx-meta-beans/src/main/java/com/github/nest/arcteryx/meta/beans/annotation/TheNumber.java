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
import com.github.nest.arcteryx.meta.beans.constraints.TheNumberConstraint;

/**
 * number
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(constraintClass = TheNumberConstraint.class)
public @interface TheNumber {
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
		TheNumber[] values();
	}

	/**
	 * min
	 * 
	 * @return
	 */
	double min() default Double.NEGATIVE_INFINITY;

	/**
	 * exclude min
	 * 
	 * @return
	 */
	boolean excludeMin() default false;

	/**
	 * max
	 * 
	 * @return
	 */
	double max() default Double.POSITIVE_INFINITY;

	/**
	 * exclude max
	 * 
	 * @return
	 */
	boolean excludeMax() default false;
}
