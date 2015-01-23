/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.annotation;

import com.github.nest.arcteryx.meta.beans.IBeanConstraintReorganizer;

/**
 * constraint reorganizer
 * 
 * @author brad.wu
 */
public @interface BeanConstraintReorganizer {
	/**
	 * class of reorganizer
	 * 
	 * @return
	 */
	Class<? extends IBeanConstraintReorganizer> reorganizerClass();
}
