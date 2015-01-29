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
 * resource annotation, to declare an annotation is for annotating a resource.<br>
 * eg. 
 * <pre>
 * &#064;Resource(generator = BeanGenerator.class)
 * public &#064;interface Bean {
 * }
 * 
 * &#064;Bean
 * public Person {
 * }
 * </pre>
 * 
 * @author brad.wu
 */
@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArcteryxResource {
	/**
	 * generator class
	 * 
	 * @return
	 */
	Class<? extends IResourceDescriptorGenerator> generator();
}
