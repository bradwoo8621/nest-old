/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

import java.util.List;

/**
 * Bean finder
 * 
 * @author brad.wu
 */
public interface IBeanFinder extends IBeanOperator {
	String CODE = "meta.beans.finder";

	/**
	 * find beans by given criteria
	 * 
	 * @param criteria
	 * @return
	 */
	<T> List<T> find(IBeanCriteria criteria);

	/**
	 * find bean by given beanIdentity
	 * 
	 * @param beanIdentity
	 * @return
	 */
	<T> T find(IBeanIdentity beanIdentity);
}
