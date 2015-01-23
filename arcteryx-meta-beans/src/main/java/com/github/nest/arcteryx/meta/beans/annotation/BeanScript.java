/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.constraints.BeanScriptConstraint;

/**
 * bean script
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Constraint(constraintClass = BeanScriptConstraint.class)
public @interface BeanScript {
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
	 * script, eg. groovy:_this.address != null
	 * 
	 * @return
	 */
	String script();

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE })
	@Constraints
	public @interface List {
		BeanScript[] values();
	}
}
