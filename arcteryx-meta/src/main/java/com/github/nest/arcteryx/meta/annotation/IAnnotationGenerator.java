/**
 * 
 */
package com.github.nest.arcteryx.meta.annotation;

import java.lang.annotation.Annotation;

/**
 * annotation generator. keep a constructor without parameter.
 * 
 * @author brad.wu
 */
public interface IAnnotationGenerator {
	/**
	 * generate annotation
	 * 
	 * @param annotation
	 * @return
	 */
	<T> T generate(Annotation annotation);
}
