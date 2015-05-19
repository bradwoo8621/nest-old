/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * human race
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = Race.CODE_TABLE_NAME)
public class Race extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -748069456845199254L;
	public final static String CODE_TABLE_NAME = "Race";

	public final static String CAUCASION = "C";
	public final static String MONGOLOID = "M";
	public final static String NEGROID = "N";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { CAUCASION, MONGOLOID, NEGROID };
	}
}
