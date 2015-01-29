/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * resource property annotation, to declare an annotation is for annotating a
 * resource property.<br>
 * 
 * @author brad.wu
 */
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArcteryxResourceProperty {
	/**
	 * generator class
	 * 
	 * @return
	 */
	Class<? extends IResourcePropertyDescriptorGenerator> generator();
}
