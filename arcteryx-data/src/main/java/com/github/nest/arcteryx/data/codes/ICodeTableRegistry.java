/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

import java.util.Collection;

/**
 * code table registry
 * 
 * @author brad.wu
 */
public interface ICodeTableRegistry {
	/**
	 * get code table by given code table name
	 * 
	 * @param codeTableName
	 * @return
	 */
	<T extends ICodeTable> T getCodeTable(String codeTableName);

	/**
	 * get all code tables
	 * 
	 * @return
	 */
	Collection<ICodeTable> all();

	/**
	 * register code table
	 * 
	 * @param codeTable
	 * @param codeTableClass
	 */
	void register(ICodeTable codeTable);

	/**
	 * deregister code table by given code table
	 * 
	 * @param codeTable
	 */
	void deregister(ICodeTable codeTable);

	/**
	 * deregister code table by given code table name
	 * 
	 * @param codeTableName
	 */
	void deregister(String codeTableName);
}
