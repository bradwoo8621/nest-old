/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;

/**
 * party role type
 * 
 * @author brad.wu
 */
public interface IPartyRoleType extends Serializable {
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
