/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * gender
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = Gender.CODE_TABLE_NAME)
public class Gender extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -3646606423019520379L;

	public static final String CODE_TABLE_NAME = "Gender";

	public static final String FEMALE = "F";
	public static final String MALE = "M";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { FEMALE, MALE };
	}
}
