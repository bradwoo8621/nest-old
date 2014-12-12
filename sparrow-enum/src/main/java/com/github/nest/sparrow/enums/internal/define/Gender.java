/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import com.github.nest.sparrow.enums.define.IGender;
import com.github.nest.sparrow.enums.internal.CodedEnumItem;

/**
 * Gender
 * 
 * @author brad.wu
 */
public class Gender extends CodedEnumItem implements IGender {
	private static final long serialVersionUID = 1884778534893654701L;

	public Gender(String id, String label, String code) {
		super(id, label, code);
	}
}
