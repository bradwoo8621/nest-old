/**
 * 
 */
package com.github.nest.arcteryx.data.codes.ut06;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * @author brad.wu
 */
@CodeTableRegistration(registerClass = CodeTableA.class, name = "CodeTableA")
public class CodeTableA extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -4676077845092953939L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { "CodeA", "CodeB", "CodeC" };
	}
}
