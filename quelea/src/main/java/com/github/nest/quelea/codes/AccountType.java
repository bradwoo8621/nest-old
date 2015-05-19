/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * account type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = AccountType.CODE_TABLE_NAME)
public class AccountType extends AbstractCodeTable {
	private static final long serialVersionUID = 1678664430696295872L;

	public static final String CODE_TABLE_NAME = "AccountType";

	public static final String AUTO_DEBIT = "1";
	public static final String CREDIT_CARD = "2";
}
