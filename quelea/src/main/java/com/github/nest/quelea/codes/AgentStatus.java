/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * agent status
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = AgentStatus.CODE_TABLE_NAME)
public class AgentStatus extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -1159428630228229216L;

	public final static String CODE_TABLE_NAME = "AgentStatus";

	public final static String SUBMITTED = "1";
	public final static String APPROVED = "2";
	public final static String TERMINATED = "3";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { SUBMITTED, APPROVED, TERMINATED };
	}
}
