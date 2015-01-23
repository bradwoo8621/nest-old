/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraintReorganizer;

/**
 * constraint reorganizer
 * 
 * @author brad.wu
 */
public @interface BeanPropertyConstraintReorganizer {
	/**
	 * class of reorganizer
	 * 
	 * @return
	 */
	Class<? extends IBeanPropertyConstraintReorganizer> reorganizerClass();
}
