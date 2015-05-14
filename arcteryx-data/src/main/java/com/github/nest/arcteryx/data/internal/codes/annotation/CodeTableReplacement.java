/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.nest.arcteryx.data.codes.ICodeTable;

/**
 * Code table replacement.
 * 
 * @author brad.wu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@CodeTable
public @interface CodeTableReplacement {
	/**
	 * replace
	 * 
	 * @return
	 */
	Class<? extends ICodeTable> replace();
}
