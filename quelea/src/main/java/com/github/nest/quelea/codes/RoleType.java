/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * role type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = RoleType.CODE_TABLE_NAME)
public class RoleType extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -6678975060777628003L;

	public static final String CODE_TABLE_NAME = "RoleType";

	public static final String BANK = "BNK";

	public static final String AGENT_INDIVIDUAL = "AGI";
	public static final String AGENT_ORGANIZATION = "AGO";

	public static final String CUSTOMER_INDIVIDUAL = "CTI";
	public static final String CUSTOMER_ORGANIZATION = "CTO";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { BANK, //
				AGENT_INDIVIDUAL, AGENT_ORGANIZATION, //
				CUSTOMER_INDIVIDUAL, CUSTOMER_ORGANIZATION, //
		};
	}
}
