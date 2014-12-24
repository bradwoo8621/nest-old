/**
 * 
 */
package com.github.nest.arcteryx.validation.oval;

import net.sf.oval.configuration.annotation.IsInvariant;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

/**
 * @author brad.wu
 *
 */
public class MaxLengthObject {
	private String name = null;

	@IsInvariant
	@NotNull
	@Length(max = 4)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
