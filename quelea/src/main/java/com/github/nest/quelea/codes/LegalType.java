/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * legal type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = LegalType.CODE_TABLE_NAME)
public class LegalType extends AbstractCodeTable {
	private static final long serialVersionUID = -1974993339761901518L;
	public final static String CODE_TABLE_NAME = "LegalType";

	public final static String SOLE_PROPRIETOR = "1";
	public final static String PRIVATE_LIMITED = "2";
	public final static String PARTNERSHIP = "3";
	public final static String PUBLIC_LISTED = "4";
	public final static String COOPERATIVES = "5";
	public final static String OTHER = "99";
}
