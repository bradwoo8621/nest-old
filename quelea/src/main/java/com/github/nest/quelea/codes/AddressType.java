/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * address type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = AddressType.CODE_TABLE_NAME)
public class AddressType extends AbstractCodeTable {
	private static final long serialVersionUID = -494502612274087430L;

	public static final String CODE_TABLE_NAME = "AddressType";

	public static final String HOME = "1";
	public static final String COMMUNICATE = "2";
	public static final String COMPANY = "3";
	public static final String OTHER = "99";
}
