/**
 * 
 */
package com.github.nest.sparrow.party.codes;

import com.github.nest.goose.ICodeBaseBean;

/**
 * party type
 * 
 * @author brad.wu
 */
public interface IPartyType extends ICodeBaseBean {
	/**
	 * individual
	 */
	String INDIVIDUAL = "I";
	/**
	 * organization
	 */
	String ORGANIZATION = "O";

	/**
	 * get name of party type
	 * 
	 * @return
	 */
	String getName();
}
