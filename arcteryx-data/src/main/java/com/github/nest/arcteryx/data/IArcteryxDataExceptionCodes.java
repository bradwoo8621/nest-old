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
	String CODETABLE_CONSTRUCT_FAIL = "NEST-00000001";
	/**
	 * initialize code table error
	 */
	String CODETABLE_INITIAL_FAIL = "NEST-00000002";
	/**
	 * construct code table provider error
	 */
	String CODETABLE_CONTENT_PROVIDER_CONSTRUCT_FAIL = "NEST-00000003";
	/**
	 * annotation in code table class invalid error
	 */
	String CODETABLE_ANNOTATION_INVALID = "NEST-00000004";
}
