/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;

/**
 * multiple reference, many-to-many relationship.<br>
 * life-cycle of the referenced bean is managed manually.
 * 
 * @author brad.wu
 */
public interface IManyToManyPersistentColumn extends IPersistentColumn {
	/**
	 * get referenced bean.
	 * 
	 * @return
	 */
	IBeanDescriptor getReferencedBean();

	/**
	 * get referenced bean context name. bean can be referenced from another
	 * context.
	 * 
	 * @return
	 */
	String getReferencedBeanContextName();

	/**
	 * get subordinate bean.
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getSubordinateBean();

	/**
	 * check the referenced bean is in same context with this or not
	 * 
	 * @return
	 */
	boolean isInSameContext();
}
