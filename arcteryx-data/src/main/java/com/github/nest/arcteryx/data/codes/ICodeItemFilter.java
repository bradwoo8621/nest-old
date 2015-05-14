/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

/**
 * code item filter
 * 
 * @author brad.wu
 */
public interface ICodeItemFilter {
	/**
	 * accept the code item or not
	 * 
	 * @param item
	 * @return
	 */
	boolean accept(ICodeItem item);
}
