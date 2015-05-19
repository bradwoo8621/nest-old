/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * individual id type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = IndividualIdType.CODE_TABLE_NAME)
public class IndividualIdType extends AbstractCodeTable {
	private static final long serialVersionUID = 1158453067733447285L;
	public static final String CODE_TABLE_NAME = "IndividualType";

	public static final String ID_CARD = "1";
	public static final String RESIDENCE_PERMIT = "2";
	public static final String MILITARY_ID = "3";
	public static final String PASSPORT = "4";
	public static final String DRIVE_LICENSE = "5";
	public static final String VIRTUAL_NUMBER = "99";
}
