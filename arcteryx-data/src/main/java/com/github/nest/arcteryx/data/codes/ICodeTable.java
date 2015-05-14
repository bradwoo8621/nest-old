/**
 * 
 */
package com.github.nest.arcteryx.data.codes;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * code table
 * 
 * @author brad.wu
 */
public interface ICodeTable extends Serializable {
	/**
	 * get name of code table
	 * 
	 * @return
	 */
	String getName();

	/**
	 * check the given code is contained or not
	 * 
	 * @param code
	 * @return
	 */
	boolean contains(String code);

	/**
	 * get item by given code, return null when not found.
	 * 
	 * @param code
	 * @return
	 */
	ICodeItem getItem(String code);

	/**
	 * get items iterator
	 * 
	 * @return
	 */
	Iterator<ICodeItem> iterator();

	/**
	 * filter items by given filter
	 * 
	 * @param filter
	 * @return
	 */
	Collection<ICodeItem> getItems(ICodeItemFilter filter);

	/**
	 * filter items by give table filter
	 * 
	 * @param filter
	 * @return
	 */
	Collection<ICodeItem> getItems(ICodeTableFilter filter);

	/**
	 * get items
	 * 
	 * @return
	 */
	Collection<ICodeItem> getItems();
}
