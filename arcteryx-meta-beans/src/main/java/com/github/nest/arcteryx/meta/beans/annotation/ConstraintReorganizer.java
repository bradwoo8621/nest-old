/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.annotation.scan.IConstraintReorganizerGenerator;

/**
 * constraint reorganizer. to annotate an annotation is annotating a constraint
 * reorgranizer.
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE })
public @interface ConstraintReorganizer {
	/**
	 * generator class
	 * 
	 * @return
	 */
	Class<? extends IConstraintReorganizerGenerator> generator();
}
