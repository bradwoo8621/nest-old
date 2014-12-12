/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * Bean destroyer
 * 
 * @author brad.wu
 */
public interface IBeanDestroyer extends IBeanOperator {
	String CODE = "meta.beans.destroyer";

	/**
	 * destroy resource
	 * 
	 * @param resource
	 * @return
	 */
	boolean destroy(Object resource);
}
