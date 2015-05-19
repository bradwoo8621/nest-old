/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * credit card type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = CreditCardType.CODE_TABLE_NAME)
public class CreditCardType extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = 3081968990914878454L;
	public final static String CODE_TABLE_NAME = "CreditCardType";

	public final static String AMERICAN_EXPRESS = "1";
	public final static String DINERS_CLUB = "2";
	public final static String DISCOVER = "3";
	public final static String JCB = "4";
	public final static String MASTER = "5";
	public final static String VISA = "6";
	public final static String UNIPAY = "7";
	public final static String OTHER = "99";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { AMERICAN_EXPRESS, DINERS_CLUB, DISCOVER, JCB, MASTER, VISA, UNIPAY, OTHER };
	}
}
