/**
 * 
 */
package com.github.nest.arcteryx.data;

import com.github.nest.arcteryx.common.INestExceptionCodes;

/**
 * arcteryx-data exception codes<br>
 * 1- 10000
 * 
 * @author brad.wu
 */
public interface IArcteryxDataExceptionCodes extends INestExceptionCodes {
	/**
	 * construct code table error
	 */
	String CODE_TABLE_CONSTRUCT = "NEST-00000001";
	/**
	 * initialize code table error
	 */
	String CODE_TABLE_INITIAL = "NEST-00000002";
	/**
	 * construct code table provider error
	 */
	String CODE_TABLE_CONTENT_PROVIDER_CONSTRUCT = "NEST-00000003";
	/**
	 * annotation in code table class invalid error
	 */
	String CODE_TABLE_ANN_INVALID = "NEST-00000004";
}
