/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * subordinate persistent column, one-to-many relationship.<br>
 * life-cycle of the subordinate bean is same as current bean itself.
 * 
 * @author brad.wu
 */
public interface IOneToManyPersistentColumn extends IPersistentColumn {
	/**
	 * get subordinate bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getSubordinateBean();
}
