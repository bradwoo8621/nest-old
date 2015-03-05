/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * standalone persistent bean descriptor
 * 
 * @author brad.wu
 */
public interface IStandalonePersistentBeanDescriptor extends IPersistentBeanDescriptor {
	/**
	 * get table name of bean
	 * 
	 * @return
	 */
	String getTableName();

	/**
	 * get joined tables. joined tables provide the ability that for one class,
	 * in persistent layer, can save into more than one table.
	 * 
	 * @return
	 */
	String[] getJoinedTableNames();

	/**
	 * check the property is in joined table or not. return false when the
	 * property is not in joined table or there is no joined table.
	 * 
	 * @param propertyName
	 * @return
	 */
	boolean isJoined(String propertyName);

	/**
	 * get joined table name by given property. return null if the property is
	 * not in joined table or there is no joined table.
	 * 
	 * @param propertyName
	 * @return
	 */
	String getJoinedTableName(String propertyName);

	/**
	 * get joined table primary key column name
	 * 
	 * @param joinedTableName
	 * @return
	 */
	String getJoinedTablePrimaryKeyColumnName(String joinedTableName);
}
