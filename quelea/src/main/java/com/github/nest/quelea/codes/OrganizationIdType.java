/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * organization id type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = OrganizationIdType.CODE_TABLE_NAME)
public class OrganizationIdType extends AbstractCodeTable {
	private static final long serialVersionUID = -3321609241895929901L;
	public static final String CODE_TABLE_NAME = "OrganizationIdType";

	public static final String ID_CARD = "1";
	public static final String VIRTUAL_NUMBER = "99";
}
