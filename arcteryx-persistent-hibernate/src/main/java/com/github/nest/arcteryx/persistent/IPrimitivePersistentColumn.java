/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * primitive persistent column
 * 
 * @author brad.wu
 */
public interface IPrimitivePersistentColumn extends IPersistentColumn {
	/**
	 * get column name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get type of column
	 * 
	 * @return
	 */
	PrimitiveColumnType getType();

	/**
	 * is primary key column or not
	 * 
	 * @return
	 */
	boolean isPrimaryKey();

	/**
	 * is version column or not
	 * 
	 * @return
	 */
	boolean isVersion();
}
