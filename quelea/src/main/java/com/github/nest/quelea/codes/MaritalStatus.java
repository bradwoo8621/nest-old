/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * marital status
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = MaritalStatus.CODE_TABLE_NAME)
public class MaritalStatus extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -8688577447140512608L;
	public static final String CODE_TABLE_NAME = "MaritalStatus";

	public static final String MARRIED = "M";
	public static final String SINGLE = "S";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { MARRIED, SINGLE };
	}
}
