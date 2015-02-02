/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.util.Map;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;

/**
 * reference persistent column, many-to-one relationship.<br>
 * TODO how to describe the persistent columns if the referenced bean doesn't
 * need to be persistent?
 * 
 * @author brad.wu
 */
public interface IReferencePersistentColumn extends IPersistentColumn {
	/**
	 * get referenced bean. <br>
	 * TODO enumeration?
	 * 
	 * @return
	 */
	IBeanDescriptor getReferencedBean();

	/**
	 * get relation properties.<br>
	 * Key: property of persistent bean itself,<br>
	 * Value: property of reference bean.
	 * 
	 * @return
	 */
	Map<String, String> getRelationProperties();
}
