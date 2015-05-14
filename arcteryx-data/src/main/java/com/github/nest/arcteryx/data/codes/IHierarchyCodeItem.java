/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

/**
 * hierarchy code item
 * 
 * @author brad.wu
 */
public interface IHierarchyCodeItem extends ICodeItem {
	/**
	 * get parent value
	 * 
	 * @return
	 */
	String getParentValue();
}
