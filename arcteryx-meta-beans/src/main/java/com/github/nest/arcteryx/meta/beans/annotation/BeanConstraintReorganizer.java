/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.annotation.scan.BeanConstraintReorganizerGenerator;

/**
 * constraint reorganizer
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@ConstraintReorganizer(generator = BeanConstraintReorganizerGenerator.class)
public @interface BeanConstraintReorganizer {
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
	 * overwrite all or not, default is true
	 * 
	 * @return
	 */
	boolean overwrite() default true;
}
