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

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.meta.beans.internal.BeanDescriptor;

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

	/**
	 * descriptor class, default is {@linkplain BeanDescriptor}
	 * 
	 * @return
	 */
	Class<? extends IBeanDescriptor> descriptorClass() default BeanDescriptor.class;
}
