/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.List;

/**
 * party interface
 * 
 * @author brad.wu
 */
public interface IParty {
	/**
	 * get identity of party. Usually, identity is unique.
	 * 
	 * @return
	 */
	Long getId();

	/**
	 * get name of party
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get code of party. code is unique in whole system.
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * get addresses of party
	 * 
	 * @return
	 */
	List<IAddress> getAddresses();
}
