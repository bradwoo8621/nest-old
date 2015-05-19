/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * occupation
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = Occupation.CODE_TABLE_NAME)
public class Occupation extends AbstractCodeTable {
	private static final long serialVersionUID = -2097429379122443839L;
	public static final String CODE_TABLE_NAME = "Occupation";
}
