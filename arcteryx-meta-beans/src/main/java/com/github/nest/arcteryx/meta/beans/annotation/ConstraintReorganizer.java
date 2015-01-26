/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.IConstraintReorganizer;

/**
 * constraint reorganizer
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE })
public @interface ConstraintReorganizer {
	/**
	 * reorganizer class
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends IConstraintReorganizer> reorganizerClass();
}
