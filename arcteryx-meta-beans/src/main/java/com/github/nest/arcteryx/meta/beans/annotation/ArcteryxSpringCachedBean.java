/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.annotation.ArcteryxResource;
import com.github.nest.arcteryx.meta.beans.annotation.scan.SpringCachedBeanDescriptorGenerator;
import com.github.nest.arcteryx.meta.beans.internal.SpringCachedBeanDescriptor;

/**
 * spring cached bean parameters.<br>
 * when {@linkplain ArcteryxBean#descriptorClass()} is
 * {@linkplain SpringCachedBeanDescriptor}, add this annotation to type.
 * {@linkplain ArcteryxCachedBean} is also necessary.
 * 
 * @author brad.wu
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArcteryxResource(generator = SpringCachedBeanDescriptorGenerator.class)
public @interface ArcteryxSpringCachedBean {
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

	/**
	 * cache name
	 * 
	 * @return
	 */
	String cacheName();

	/**
	 * default sorter code, optional
	 * 
	 * @return
	 */
	String defaultSorterCode() default "";

	/**
	 * spring context id
	 * 
	 * @return
	 */
	String springContextId();

	/**
	 * spring bean id
	 * 
	 * @return
	 */
	String springBeanId();
}
