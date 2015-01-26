/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

/**
 * default value
 * 
 * @author brad.wu
 */
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
