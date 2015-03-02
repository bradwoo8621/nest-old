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
	public static final CascadeType[] DEFAULT_CASCADE_TYPES = { CascadeType.SAVE_UPDATE, CascadeType.DELETE };

	/**
	 * get subordinate bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getSubordinateBean();

	/**
	 * get cascade types
	 * 
	 * @return
	 */
	CascadeType[] getCascadeTypes();
}
