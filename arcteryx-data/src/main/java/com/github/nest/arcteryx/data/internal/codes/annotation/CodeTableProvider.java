/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;

/**
 * code table provider
 * 
 * @author brad.wu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeTableProvider {
	/**
	 * provider class
	 * 
	 * @return
	 */
	Class<? extends ICodeTableContentProvider> contentProviderClass();
}
