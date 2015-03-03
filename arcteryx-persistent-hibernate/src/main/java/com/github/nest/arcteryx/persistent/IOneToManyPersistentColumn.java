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
	public static final CascadeType[] DEFAULT_CASCADE_TYPES = { CascadeType.SAVE_UPDATE, CascadeType.DELETE };

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
	 * get cascade types
	 * 
	 * @return
	 */
	CascadeType[] getCascadeTypes();

	/**
	 * get collection parameter
	 * 
	 * @return
	 */
	ICollectionParameter getCollectionParameter();
}
