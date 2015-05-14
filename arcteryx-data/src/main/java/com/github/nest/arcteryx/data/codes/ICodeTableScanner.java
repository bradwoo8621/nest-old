/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

/**
 * code table scanner
 * 
 * @author brad.wu
 */
public interface ICodeTableScanner {
	/**
	 * scan
	 * 
	 * @param registry
	 */
	void scan(ICodeTableRegistry registry);
}
