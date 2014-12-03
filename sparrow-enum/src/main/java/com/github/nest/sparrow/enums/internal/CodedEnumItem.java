/**
 * 
 */
package com.github.nest.sparrow.enums.internal;

import com.github.nest.sparrow.enums.ICodedEnumItem;

/**
 * coded enumeration item
 * 
 * @author brad.wu
 */
public class CodedEnumItem extends EnumItem implements ICodedEnumItem {
	private static final long serialVersionUID = 3372422459979909051L;
	private String code = null;

	public CodedEnumItem(String id, String label, String code) {
		super(id, label);

		assert code != null && code.trim().length() != 0 : "Code cannot be null or empty string.";
		
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.ICodedEnumItem#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.originalHashCode() + " [id=" + getId() + ", code=" + code + ", label=" + getLabel() + "]";
	}
}
