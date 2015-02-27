/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;

/**
 * reference persistent column, many-to-one relationship.<br>
 * referenced bean can be persistent or in cache.
 * 
 * @author brad.wu
 */
public interface IManyToOnePersistentColumn extends IPersistentColumn {
	/**
	 * get referenced bean. <br>
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
	 * get foreign key column name
	 * 
	 * @return
	 */
	String getForeignKeyColumnName();

	/**
	 * get foreign key property name
	 * 
	 * @return
	 */
	String getForeignKeyPropertyName();
}
