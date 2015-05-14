/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

import java.util.List;

/**
 * code table filter
 * 
 * @author brad.wu
 */
public interface ICodeTableFilter {
	/**
	 * filter code table items
	 * 
	 * @param codeTable
	 * @return
	 */
	List<ICodeItem> filter(ICodeTable codeTable);
}
