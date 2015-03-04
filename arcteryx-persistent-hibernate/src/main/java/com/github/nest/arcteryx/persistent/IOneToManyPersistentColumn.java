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

	/**
	 * get foreign key property name of subordinate bean, which is linked to
	 * primary key of current bean itself.
	 * 
	 * @return
	 */
	String getForeignKeyPropertyName();

	/**
	 * get foreign key column name of subordinate bean, which is linked to
	 * primary key of current bean itself.
	 * 
	 * @return
	 */
	String getForeignKeyColumnName();

	/**
	 * get collection parameter
	 * 
	 * @return
	 */
	ICollectionParameter getCollectionParameter();
}
