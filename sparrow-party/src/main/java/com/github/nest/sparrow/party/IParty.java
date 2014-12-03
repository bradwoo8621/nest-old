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
	String getIdentity();

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
	 * get roles of party. at least one party role should be defined in system.
	 * 
	 * @return
	 */
	List<IPartyRole> getRoles();

	/**
	 * get addresses of party
	 * 
	 * @return
	 */
	List<IAddress> getAddresses();

	/**
	 * get addresses by given party role
	 * 
	 * @param role
	 * @return
	 */
	List<IAddress> getAddressesByRole(IPartyRole role);

	/**
	 * get default address
	 * 
	 * @return
	 */
	IAddress getDefaultAddress();

	/**
	 * get default address by given party role
	 * 
	 * @param role
	 * @return
	 */
	IAddress getDefaultAddressByRole(IPartyRole role);

	/**
	 * get accounts of party
	 * 
	 * @return
	 */
	List<IAccount> getAccounts();

	/**
	 * get default account
	 * 
	 * @return
	 */
	IAccount getDefaultAccount();

	/**
	 * get related parties
	 * 
	 * @return
	 */
	List<IRelatedParty> getRelatedParties();
}
