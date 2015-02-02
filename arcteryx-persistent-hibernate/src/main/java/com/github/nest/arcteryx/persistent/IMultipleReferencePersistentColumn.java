/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.util.Map;

/**
 * multiple reference, many-to-many relationship.
 * 
 * @author brad.wu
 */
public interface IMultipleReferencePersistentColumn extends IPersistentColumn {
	/**
	 * get referenced bean.
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getReferencedBean();

	/**
	 * get relation properties of referenced bean to subordinate bean.<br>
	 * Key: property of subordinate bean,<br>
	 * Value: property of referenced bean.
	 * 
	 * @return
	 */
	Map<String, String> getReferencedRelationProperties();

	/**
	 * get subordinate bean.
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getSubordinateBean();

	/**
	 * get relation properties of persistent bean to subordinate bean.<br>
	 * Key: property of persistent bean itself,<br>
	 * Value: property of subordinate bean.
	 * 
	 * @return
	 */
	Map<String, String> getSubordinateRelationProperties();
}
