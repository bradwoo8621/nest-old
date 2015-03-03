/**
 * 
 */
package com.github.nest.arcteryx.persistent;

import com.github.nest.arcteryx.meta.beans.IBeanOperator;

/**
 * bean persistent saver
 * 
 * @author brad.wu
 */
public interface IPersistentBeanSaver extends IBeanOperator {
	String CODE = "persistent.beans.saver";

	/**
	 * insert
	 * 
	 * @param resource
	 */
	void insert(Object resource);

	/**
	 * update
	 * 
	 * @param resource
	 */
	void update(Object resource);

	/**
	 * save. insert or update
	 * 
	 * @param resource
	 */
	void save(Object resource);

	/**
	 * remove.
	 * 
	 * @param resource
	 */
	void remove(Object resource);
}
