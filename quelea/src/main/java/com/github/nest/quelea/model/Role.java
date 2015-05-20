/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.List;

import com.github.nest.arteryx.persistent.AbstractVersionAuditable;

/**
 * role of party.<br>
 * party plays different roles in different business scenarios, which means for
 * a certain party, can have more than one roles in system.
 * 
 * @author brad.wu
 */
public class Role extends AbstractVersionAuditable implements Serializable {
	private static final long serialVersionUID = -3922692867497747575L;

	private Long roleId = null;
	private String roleCode = null;
	private String roleTypeCode = null;
	private Account defaultAccount = null;
	private Address defaultAddress = null;
	private List<Account> accounts = null;
	private List<Address> addresses = null;
	private Boolean enabled = null;
	private Party party = null;

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * @return the roleTypeCode
	 */
	public String getRoleTypeCode() {
		return roleTypeCode;
	}

	/**
	 * @param roleTypeCode
	 *            the roleTypeCode to set
	 */
	public void setRoleTypeCode(String roleTypeCode) {
		this.roleTypeCode = roleTypeCode;
	}

	/**
	 * @return the defaultAccount
	 */
	public Account getDefaultAccount() {
		return defaultAccount;
	}

	/**
	 * @param defaultAccount
	 *            the defaultAccount to set
	 */
	public void setDefaultAccount(Account defaultAccount) {
		this.defaultAccount = defaultAccount;
	}

	/**
	 * @return the defaultAddress
	 */
	public Address getDefaultAddress() {
		return defaultAddress;
	}

	/**
	 * @param defaultAddress
	 *            the defaultAddress to set
	 */
	public void setDefaultAddress(Address defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the party
	 */
	public Party getParty() {
		return party;
	}

	/**
	 * @param party
	 *            the party to set
	 */
	public void setParty(Party party) {
		this.party = party;
	}
}
