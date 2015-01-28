/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;

/**
 * arcteryx bean
 * 
 * @author brad.wu
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope("prototype")
public @interface ArcteryxBean {
	/**
	 * bean name in bean context. keep unique.
	 * 
	 * @return
	 */
	String name();

	/**
	 * description of bean, optional
	 * 
	 * @return
	 */
	String description() default "";
}
