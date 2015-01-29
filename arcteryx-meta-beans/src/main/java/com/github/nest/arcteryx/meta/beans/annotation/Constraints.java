/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * constraints. to annotated a constraint annotation which used in
 * type/field/method level and the annotation is a set of constraint.
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Constraints {
}
