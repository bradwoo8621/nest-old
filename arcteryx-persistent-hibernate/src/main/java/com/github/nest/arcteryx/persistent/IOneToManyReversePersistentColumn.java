/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * one-to-many reverse persistent column
 * 
 * @author brad.wu
 */
public interface IOneToManyReversePersistentColumn extends IPersistentColumn {
	/**
	 * get parent bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getParentBean();
}
