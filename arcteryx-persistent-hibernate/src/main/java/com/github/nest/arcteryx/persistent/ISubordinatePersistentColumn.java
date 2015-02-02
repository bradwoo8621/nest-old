/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.util.Map;

/**
 * subordinate persistent column, one-to-many relationship.
 * 
 * @author brad.wu
 */
public interface ISubordinatePersistentColumn extends IPersistentColumn {
	/**
	 * get subordinate bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getSubordinateBean();

	/**
	 * get relation properties.<br>
	 * Key: property of persistent bean itself,<br>
	 * Value: property of subordinate bean.
	 * 
	 * @return
	 */
	Map<String, String> getRelationProperties();
}
