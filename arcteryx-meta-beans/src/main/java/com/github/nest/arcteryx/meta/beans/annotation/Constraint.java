/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.IConstraint;

/**
 * constraint. to annotated a constraint annotation which used in
 * type/field/method level.
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Constraint {
	/**
	 * convert by
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends IConstraint> constraintClass();
}
