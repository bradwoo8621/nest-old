/**
 * 
 */
package com.github.nest.arcteryx.persistent;

/**
 * embedded persistent column. <br>
 * 
 * @author brad.wu
 */
public interface IEmbeddedPersistentColumn extends IPersistentColumn {
	/**
	 * get embedded bean. <br>
	 * 
	 * @return
	 */
	IEmbeddablePersistentBeanDescriptor getEmbeddedBean();

	/**
	 * get referenced bean context name. bean can be referenced from another
	 * context.
	 * 
	 * @return
	 */
	String getReferencedBeanContextName();

	/**
	 * get overridden column name by original column name
	 * 
	 * @param propertyName
	 * @return
	 */
	String getOverriddenColumnName(String propertyName);
}
