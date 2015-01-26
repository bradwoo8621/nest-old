/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.meta.beans.internal.SpringCachedBeanDescriptor;

/**
 * spring cached bean parameters.<br>
 * when {@linkplain ArcteryxBean#descriptorClass()} is
 * {@linkplain SpringCachedBeanDescriptor}, add this annotation to type.
 * {@linkplain CachedBeanParameters} is also necessary.
 * 
 * @author brad.wu
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpringCachedBeanParameters {
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
