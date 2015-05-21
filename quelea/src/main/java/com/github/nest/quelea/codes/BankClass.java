/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * bank class
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = BankClass.CODE_TABLE_NAME)
public class BankClass extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -1764630011993690025L;

	public final static String CODE_TABLE_NAME = "BankClass";

	public final static String AAA = "1";
	public final static String AAPlus = "2";
	public final static String AA = "3";
	public final static String AAMinus = "4";
	public final static String APlus = "5";
	public final static String A = "6";
	public final static String AMinus = "7";
	public final static String BBBPlus = "8";
	public final static String BBB = "9";
	public final static String BBBMinus = "10";
	public final static String BBPlus = "11";
	public final static String BB = "12";
	public final static String BBMinus = "13";
	public final static String BPlus = "14";
	public final static String B = "15";
	public final static String BMinus = "16";
	public final static String CCCPlus = "17";
	public final static String CCC = "18";
	public final static String CCCMinus = "19";
	public final static String CC = "20";
	public final static String C = "21";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { AAA, AAPlus, AA, AAMinus, APlus, A, AMinus, BBBPlus, BBB, BBBMinus, BBPlus, BB, BBMinus,
				BPlus, B, BMinus, CCCPlus, CCC, CCCMinus, CC, C };
	}
}
