/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import java.io.Serializable;

import com.github.nest.arcteryx.meta.beans.IBeanOperator;

/**
 * persistent bean loader
 * 
 * @author brad.wu
 */
public interface IPersistentBeanLoader extends IBeanOperator {
	String CODE = "persistent.beans.loader";

	/**
	 * load by given id
	 * 
	 * @param id
	 * @return
	 */
	<T> T load(Serializable id);

	/**
	 * load into given object by given id
	 * 
	 * @param object
	 * @param id
	 * @return
	 */
	<T> T load(T object, Serializable id);
}
