/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.util.Map;

/**
 * expanded persistent column. one-to-one relationship.
 * 
 * @author brad.wu
 */
public interface IExpandedPersistentColumn extends IPersistentColumn {
	/**
	 * is physical embedded or not
	 * 
	 * @return
	 */
	boolean isPhysicalEmbedded();

	/**
	 * get relation properties. only active when
	 * {@linkplain #isPhysicalEmbedded()} returns false.<br>
	 * Key: property of persistent bean itself,<br>
	 * Value: property of embedded bean.
	 * 
	 * @return
	 */
	Map<String, String> getRelationProperties();
}
