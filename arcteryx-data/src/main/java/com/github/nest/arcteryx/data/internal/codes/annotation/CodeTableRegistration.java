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
 * Code table resource annotation
 * 
 * @author brad.wu
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@CodeTable
public @interface CodeTableRegistration {
	/**
	 * code table name, also registration name
	 * 
	 * @return
	 */
	String name();

	/**
	 * code table registration class
	 * 
	 * @return
	 */
	Class<? extends ICodeTable> registerClass();
}
