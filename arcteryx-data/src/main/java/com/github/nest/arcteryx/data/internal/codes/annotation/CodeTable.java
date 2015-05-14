/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;

/**
 * code table annotation
 * 
 * @author brad.wu
 */
@Documented
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Scope("prototype")
public @interface CodeTable {

}
