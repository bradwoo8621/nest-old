/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * one-to-one reverse parent column
 * 
 * @author brad.wu
 */
public interface IOneToOneReversePersistentColumn extends IPersistentColumn {

	/**
	 * get parent bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getParentBean();
}
