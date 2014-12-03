/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import com.github.nest.sparrow.enums.define.IOccupation;
import com.github.nest.sparrow.enums.internal.EnumItem;

/**
 * Occupation
 * 
 * @author brad.wu
 */
public class Occupation extends EnumItem implements IOccupation {
	private static final long serialVersionUID = -6495106365068657764L;

	public Occupation(String id, String label) {
		super(id, label);
	}
}
