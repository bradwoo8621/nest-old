/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.io.Serializable;

/**
 * collection parameter
 * 
 * @author brad.wu
 */
public interface ICollectionParameter extends Serializable {
	/**
	 * get type of collection
	 * 
	 * @return
	 */
	CollectionType getType();

	/**
	 * get cascade types
	 * 
	 * @return
	 */
	CascadeType[] getCascadeTypes();

	/**
	 * is inverse or not.<br>
	 * true is used in bidirectional associations. default is false.
	 * 
	 * @return
	 */
	boolean isInverse();

	/**
	 * add parameter
	 * 
	 * @param key
	 * @param value
	 */
	ICollectionParameter addParameter(String key, Object value);

	/**
	 * get parameter
	 * 
	 * @param key
	 * @return
	 */
	<T> T getParameter(String key);
}
