/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import com.github.nest.arcteryx.meta.IResourceOperator;

/**
 * bean operator interface
 * 
 * @author brad.wu
 */
public interface IBeanOperator extends IResourceOperator {
	/**
	 * get bean descriptor
	 * 
	 * @return
	 */
	IBeanDescriptor getBeanDescriptor();
}
