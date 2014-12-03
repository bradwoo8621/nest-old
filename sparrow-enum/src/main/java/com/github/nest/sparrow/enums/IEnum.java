/**
 * 
 */
package com.github.nest.sparrow.enums;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * enumeration interface
 * 
 * @author brad.wu
 */
public interface IEnum extends Serializable {
	/**
	 * code of enumeration, unique key in system.
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * get items
	 * 
	 * @return
	 */
	List<IEnumItem> getItems();

	/**
	 * get sorted items by given comparator
	 * 
	 * @param comparator
	 * @return
	 */
	List<IEnumItem> getItems(Comparator<IEnumItem> comparator);

	/**
	 * get item by given identity. return null if not found.
	 * 
	 * @param id
	 * @return
	 */
	IEnumItem getItem(String id);

	/**
	 * get item by given code. return null if not found.
	 * 
	 * @param code
	 * @return
	 */
	ICodedEnumItem getItemByCode(String code);
}
