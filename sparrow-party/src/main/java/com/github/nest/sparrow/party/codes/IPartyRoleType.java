/**
 * 
 */
package com.github.nest.sparrow.party.codes;

import com.github.nest.goose.ICodeBaseBean;

/**
 * party role type
 * 
 * @author brad.wu
 */
public interface IPartyRoleType extends ICodeBaseBean {
	/**
	 * get code
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * get name
	 * 
	 * @return
	 */
	String getName();
}
