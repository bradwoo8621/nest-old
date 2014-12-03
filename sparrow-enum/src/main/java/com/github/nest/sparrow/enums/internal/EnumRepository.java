/**
 * 
 */
package com.github.nest.sparrow.enums.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.nest.sparrow.enums.IEnum;
import com.github.nest.sparrow.enums.IEnumRepository;

/**
 * enumeration repository implementation
 * 
 * @author brad.wu
 */
public class EnumRepository implements IEnumRepository {
	private Map<String, IEnum> enumMap = new HashMap<String, IEnum>();

	public EnumRepository(List<IEnum> enumList) {
		assert enumList != null && enumList.size() > 0 : "Enumeration list cannot be null.";

		for (IEnum elm : enumList) {
			enumMap.put(elm.getCode(), elm);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.IEnumRepository#get(java.lang.String)
	 */
	@Override
	public IEnum get(String code) {
		return enumMap.get(code);
	}
}
