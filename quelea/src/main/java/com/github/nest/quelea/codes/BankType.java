/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * bank type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = BankType.CODE_TABLE_NAME)
public class BankType extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = 9111588319518105312L;

	public final static String CODE_TABLE_NAME = "BankType";

	public final static String Commercial = "1";
	public final static String Community = "2";
	public final static String CommunityDevelopment = "3";
	public final static String LandDevelopment = "4";
	public final static String CreditUnions = "5";
	public final static String PostalSavings = "6";
	public final static String Private = "7";
	public final static String Offshore = "8";
	public final static String Savings = "9";
	public final static String BuildingSocieties = "10";
	public final static String Ethical = "11";
	public final static String Other = "99";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { Commercial, Community, CommunityDevelopment, LandDevelopment, CreditUnions,
				PostalSavings, Private, Offshore, Savings, BuildingSocieties, Ethical, Other };
	}
}
