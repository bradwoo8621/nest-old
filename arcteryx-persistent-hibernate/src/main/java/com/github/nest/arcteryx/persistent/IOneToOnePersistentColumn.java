/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * expanded persistent column. one-to-one relationship.<br>
 * life-cycle of the subordinate bean is same as current bean itself.
 * 
 * @author brad.wu
 */
public interface IOneToOnePersistentColumn extends IPersistentColumn {
	/**
	 * get subordinate bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getSubordinateBean();
}
