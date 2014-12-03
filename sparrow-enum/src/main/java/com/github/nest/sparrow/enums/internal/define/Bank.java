/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import com.github.nest.sparrow.enums.define.IBank;
import com.github.nest.sparrow.enums.internal.CodedEnumItem;

/**
 * Bank
 * 
 * @author brad.wu
 */
public class Bank extends CodedEnumItem implements IBank {
	private static final long serialVersionUID = 1856648258019240505L;
	private String abbreviation = null;
	
	public Bank(String id, String label, String code, String abbreviation) {
		super(id, label, code);
		this.abbreviation = abbreviation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IBank#getAbbreviation()
	 */
	@Override
	public String getAbbreviation() {
		return this.abbreviation;
	}
}
