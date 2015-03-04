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
	 * get intermediate table name
	 * 
	 * @return
	 */
	String getIntermediateTableName();

	/**
	 * get foreign key column name. the foreign key is the link between me and
	 * intermediate table.
	 * 
	 * @return
	 */
	String getForeignKeyColumnNameToMe();

	/**
	 * get foreign key column name. the foreign key is the link between
	 * intermediate table and referenced bean.
	 * 
	 * @return
	 */
	String getForeignKeyColumnNameToRefer();

	/**
	 * get foreign key property name. the foreign key is the link between
	 * intermediate table and referenced bean.<br>
	 * if the referenced bean is not a persistent bean, foreign key property
	 * name is necessary. otherwise it is not need, system will find the
	 * property name automatically.
	 * 
	 * @return
	 */
	String getForeignKeyPropertyNameToRefer();

	/**
	 * check the referenced bean is in same context with this or not
	 * 
	 * @return
	 */
	boolean isInSameContext();

	/**
	 * get collection parameter
	 * 
	 * @return
	 */
	ICollectionParameter getCollectionParameter();
}
