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
 * default value.<br>
 * 
 * @author brad.wu
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DefaultValue {
	/**
	 * default value, separator by comma
	 * 
	 * @return
	 */
	String value();

	/**
	 * format, use to convert date
	 * 
	 * @return
	 */
	String format() default "";
}
