/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.List;

import com.github.nest.quelea.codes.RoleType;

/**
 * role of party.<br>
 * party plays different roles in different business scenarios, which means for
 * a certain party, can have more than one roles in system.
 * 
 * @author brad.wu
 */
public interface IRole extends Serializable {
	/**
	 * get role id
	 * 
	 * @return
	 */
	Long getRoleId();

	/**
	 * set role id
	 * 
	 * @param roleId
	 */
	void setRoleId(Long roleId);

	/**
	 * get role code
	 * 
	 * @return
	 */
	String getRoleCode();

	/**
	 * set role code
	 * 
	 * @param roleCode
	 */
	void setRoleCode(String roleCode);

	/**
	 * get role type code
	 * 
	 * @return
	 * @see RoleType
	 */
	String getRoleTypeCode();

	/**
	 * get addresses.<br>
	 * addresses are saved as part of {@linkplain IParty}, here are references.
	 * 
	 * @return
	 */
	List<IAddress> getAddresses();

	/**
	 * set addresses
	 * 
	 * @param addresses
	 */
	void setAddresses(List<IAddress> addresses);

	/**
	 * get default address.<br>
	 * default address also can be found in property "addresses".
	 * 
	 * @return
	 */
	IAddress getDefaultAddress();

	/**
	 * set default address
	 * 
	 * @param defaultAddress
	 */
	void setDefaultAddress(IAddress defaultAddress);

	/**
	 * get accounts.<br>
	 * accounts are saved as part of {@linkplain IParty}, here are references.
	 * 
	 * @return
	 */
	List<IAccount> getAccounts();

	/**
	 * set accounts
	 * 
	 * @param accounts
	 */
	void setAccounts(List<IAccount> accounts);

	/**
	 * get default account
	 * 
	 * @return
	 */
	IAccount getDefaultAccount();

	/**
	 * set default account
	 * 
	 * @param account
	 */
	void setDefaultAccount(IAccount account);

	/**
	 * get party
	 * 
	 * @return
	 */
	IParty getParty();

	/**
	 * set party
	 * 
	 * @param party
	 */
	void setParty(IParty party);

	/**
	 * is enabled
	 * 
	 * @return
	 */
	Boolean isEnabled();

	/**
	 * set enabled
	 * 
	 * @param enabled
	 */
	void setEnabled(Boolean enabled);
}
