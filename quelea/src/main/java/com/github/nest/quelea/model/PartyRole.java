/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.github.nest.arteryx.persistent.AbstractVersionAuditable;
import com.github.nest.quelea.codes.RoleStatus;

/**
 * role of party.<br>
 * party plays different roles in different business scenarios, which means for
 * a certain party, can have more than one roles in system.
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_PARTY_ROLE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ROLE_TYPE_CODE")
@SequenceGenerator(name = "S_PARTY_ROLE", sequenceName = "S_PARTY_ROLE")
public abstract class PartyRole extends AbstractVersionAuditable implements Serializable {
	private static final long serialVersionUID = -3922692867497747575L;

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARTY_ROLE")
	private Long roleId = null;

	@Column(name = "ROLE_CODE")
	private String roleCode = null;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEFAULT_ACCOUNT_ID")
	private Account defaultAccount = null;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEFAULT_ADDRESS_ID")
	private Address defaultAddress = null;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "T_PARTY_ROLE_ACCOUNT", //
	joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"), //
	inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID"))
	private List<Account> accounts = null;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "T_PARTY_ROLE_ADDRESS", //
	joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"), //
	inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID"))
	private List<Address> addresses = null;

	@Column(name = "IS_ENABLED")
	private Boolean enabled = null;

	@Column(name = "STATUS_CODE")
	private String statusCode = null;

	@Column(name = "TERMINATED_AT")
	@Temporal(TemporalType.DATE)
	private Date terminatedAt = null;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "PARTY_ID", nullable = false, updatable = false)
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
	public abstract String getRoleTypeCode();

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
	 * set role status code
	 * 
	 * @param statusCode
	 * @see RoleStatus
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * get role status code
	 * 
	 * @return
	 * @see RoleStatus
	 */
	public String getStatusCode() {
		return this.statusCode;
	}

	/**
	 * set terminated date
	 * 
	 * @param terminatedAt
	 */
	public void setTerminatedAt(DateTime terminatedAt) {
		this.terminatedAt = null == terminatedAt ? null : terminatedAt.toDate();
	}

	/**
	 * get terminated date
	 * 
	 * @return
	 */
	public DateTime getTerminatedAt() {
		return this.terminatedAt == null ? null : new DateTime(this.terminatedAt);
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
