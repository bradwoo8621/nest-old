/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

import java.util.Collection;
import java.util.Iterator;

/**
 * code table content provider. must keep a constructor with no parameters.
 * 
 * @author brad.wu
 */
public interface ICodeTableContentProvider {
	/**
	 * initialize
	 * 
	 * @param codeTable
	 */
	void initialize(ICodeTable codeTable);

	/**
	 * check the given code is existed or not.
	 * 
	 * @param code
	 * @return
	 */
	boolean contains(String code);

	/**
	 * get item by given code
	 * 
	 * @param code
	 * @return
	 */
	ICodeItem getItem(String code);

	/**
	 * get iterator of items
	 * 
	 * @return
	 */
	Iterator<ICodeItem> iterator();

	/**
	 * get collection of items
	 * 
	 * @return
	 */
	Collection<ICodeItem> getItems();

	/**
	 * filter items by given filter
	 * 
	 * @param filter
	 * @return
	 */
	Collection<ICodeItem> getItems(ICodeItemFilter filter);

	/**
	 * filter items by give filter
	 * 
	 * @param filter
	 * @return
	 */
	Collection<ICodeItem> getItems(ICodeTableFilter filter);
}
