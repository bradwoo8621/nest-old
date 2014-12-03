/**
 * 
 */
package com.github.nest.sparrow.enums.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.nest.sparrow.enums.EnumException;
import com.github.nest.sparrow.enums.ICodedEnumItem;
import com.github.nest.sparrow.enums.IEnum;
import com.github.nest.sparrow.enums.IEnumItem;

/**
 * enumeration
 * 
 * @author brad.wu
 */
public class Enum implements IEnum {
	private static final long serialVersionUID = 4225118226361717663L;
	private String code = null;
	private Map<String, IEnumItem> mapOnId = new HashMap<String, IEnumItem>();
	private Map<String, ICodedEnumItem> mapOnCode = null;
	private List<IEnumItem> items = new ArrayList<IEnumItem>();

	public Enum(String code, List<IEnumItem> items) {
		assert code != null && code.trim().length() != 0 : "Code cannot be null for enumeration.";
		assert items != null && items.size() > 0 : "Enumeration[" + code + "], items cannot be null.";

		this.code = code;
		this.items = items;

		for (IEnumItem item : items) {
			mapOnId.put(item.getId(), item);
			if (item instanceof ICodedEnumItem) {
				mapOnCode.put(((ICodedEnumItem) item).getCode(), (ICodedEnumItem) item);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnum#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnum#getItems()
	 */
	@Override
	public List<IEnumItem> getItems() {
		List<IEnumItem> newItems = new ArrayList<IEnumItem>(this.items.size());
		newItems.addAll(this.items);
		return newItems;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnum#getItems(java.util.Comparator)
	 */
	@Override
	public List<IEnumItem> getItems(Comparator<IEnumItem> comparator) {
		List<IEnumItem> items = getItems();
		Collections.sort(items, comparator);
		return items;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnum#getItem(java.lang.String)
	 */
	@Override
	public IEnumItem getItem(String id) {
		return mapOnId.get(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnum#getItemByCode(java.lang.String)
	 */
	@Override
	public ICodedEnumItem getItemByCode(String code) {
		if (mapOnCode != null) {
			return mapOnCode.get(code);
		} else {
			throw new EnumException("Enumeration[" + getCode() + "] doesn't support code search.");
		}
	}
}
