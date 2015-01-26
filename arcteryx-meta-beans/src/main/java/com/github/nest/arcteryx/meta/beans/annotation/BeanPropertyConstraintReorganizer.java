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
 * bean property constraint reorganizer
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@ConstraintReorganizer(reorganizerClass = com.github.nest.arcteryx.meta.beans.internal.validators.BeanPropertyConstraintReorganizer.class)
public @interface BeanPropertyConstraintReorganizer {
	/**
	 * names to filter
	 * 
	 * @return
	 */
	String[] names() default {};

	/**
	 * types to filter
	 * 
	 * @return
	 */
	Class<?>[] types() default {};

	/**
	 * overwrite all or not, default is true.
	 * 
	 * @return
	 */
	boolean overwrite() default true;
}
