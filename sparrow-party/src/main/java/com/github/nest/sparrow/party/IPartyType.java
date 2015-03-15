/**
 * 
 */
package com.github.nest.sparrow.party;

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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.goose.ICodeBaseBean#getCode()
	 */
	String getCode();

	/**
	 * get name of party type
	 * 
	 * @return
	 */
	String getName();
}
