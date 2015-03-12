/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * stand-alone persistent bean descriptor<br>
 * <ol>
 * <li>stand-alone has at least one persistent table in RDBMS schema,</li>
 * <li>more than one table is allowed, use {@linkplain #getJoinedTableNames()},
 * {@linkplain #isJoined(String)}, {@linkplain #getJoinedTableName(String)} and
 * {@linkplain #getJoinedTablePrimaryKeyColumnName(String)} to describe the
 * secondary tables.</li>
 * <li>bean can be extended. such as Person can be extended by Student and
 * Worker. in persistent layer, super class "Person" and sub class
 * "Student"/"Worker" both have their own table (maybe more than one, follow the
 * rules 1 and 2), and the common properties will be stored in table of
 * "Person". and when loading, use Person as parameter, return Student or Worker
 * instead.
 * <ul>
 * <li>for super class, {@linkplain #getDiscriminatorColumnName()} to describe
 * the column which identifies the sub classes.</li>
 * <li>for sub classes, {@linkplain #getDiscriminatorValue()},
 * {@linkplain #getForeignKeyColumnName()} to describe how to link to super
 * class</li>
 * </ol>
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

	/**
	 * get extends from. see subclass concept in hibernate.
	 * 
	 * @return
	 */
	IStandalonePersistentBeanDescriptor getExtendsFrom();

	/**
	 * get foreign key (also is the primary key) column name if current bean is
	 * extends from another.
	 * 
	 * @return
	 */
	String getForeignKeyColumnName();

	/**
	 * get discriminator value
	 * 
	 * @return
	 */
	String getDiscriminatorValue();

	/**
	 * get discriminator column name
	 * 
	 * @return
	 */
	String getDiscriminatorColumnName();

	/**
	 * get entity name. for same class, there could be more than one
	 * descriptors.
	 * 
	 * @return
	 */
	String getEntityName();
}
