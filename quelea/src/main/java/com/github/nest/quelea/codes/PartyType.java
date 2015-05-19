/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * party type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = PartyType.CODE_TABLE_NAME)
public class PartyType extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = 1323231189735838980L;
	public final static String CODE_TABLE_NAME = "PartyType";

	public final static String INDIVIDUAL = "I";
	public final static String ORGANIZATION = "O";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { INDIVIDUAL, ORGANIZATION };
	}
}
