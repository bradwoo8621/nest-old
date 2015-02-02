/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.util.Map;

/**
 * embedded persistent column. one-to-one relationship or embedded columns.<br>
 * TODO how to overwrite the embedded properties?
 * 
 * @author brad.wu
 */
public interface IEmbeddedPersistentColumn extends IPersistentColumn {
	/**
	 * get embedded bean. <br>
	 * 
	 * @return
	 */
	IPersistentBeanDescriptor getEmbeddedBean();

	/**
	 * is physical embedded or not
	 * 
	 * @return
	 */
	boolean isPhysicalEmbedded();

	/**
	 * get relation properties. only active when
	 * {@linkplain #isPhysicalEmbedded()} returns false.<br>
	 * Key: property of persistent bean itself,<br>
	 * Value: property of embedded bean.
	 * 
	 * @return
	 */
	Map<String, String> getRelationProperties();
}
