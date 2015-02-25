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
}
