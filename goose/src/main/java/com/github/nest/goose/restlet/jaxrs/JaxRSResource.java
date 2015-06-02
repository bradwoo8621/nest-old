/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;

/**
 * @author brad.wu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Scope("prototype")
public @interface JaxRSResource {
	/**
	 * get version of resource
	 * 
	 * @return
	 */
	String version() default "v1";

	/**
	 * get module of resource
	 * 
	 * @return
	 */
	String module() default "system";
}
