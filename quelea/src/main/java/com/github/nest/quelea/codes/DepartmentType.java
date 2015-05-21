/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * department type
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = DepartmentType.CODE_TABLE_NAME)
public class DepartmentType extends AbstractConstantedCodeTable {
	private static final long serialVersionUID = -2962243629732042379L;

	public final static String CODE_TABLE_NAME = "DepartmentType";

	public final static String CLAIM = "1";
	public final static String FINANCE = "2";
	public final static String IT = "3";
	public final static String BUSINESS = "4";
	public final static String CALL_CENTER = "5";
	public final static String REINSURNACE = "6";

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.data.internal.codes.AbstractConstantedCodeTable#createCodes()
	 */
	@Override
	protected String[] createCodes() {
		return new String[] { CLAIM, FINANCE, IT, BUSINESS, CALL_CENTER, REINSURNACE };
	}
}
