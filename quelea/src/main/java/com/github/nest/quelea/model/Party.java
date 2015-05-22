/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.github.nest.arteryx.persistent.AbstractVersionAuditable;
import com.github.nest.quelea.codes.PartyType;

/**
 * party
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_PARTY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PARTY_TYPE_CODE")
@SequenceGenerator(name = "S_PARTY", sequenceName = "S_PARTY")
public abstract class Party extends AbstractVersionAuditable implements Serializable {
	private static final long serialVersionUID = 6672583372258942280L;

	@Id
	@Column(name = "PARTY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARTY")
	private Long partyId = null;

	@Column(name = "PARTY_CODE")
	private String partyCode = null;

	@Column(name = "ID_NUMBER")
	private String idNumber = null;

	@Column(name = "PARTY_NAME")
	private String partyName = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PARTY_ID")
	private List<Account> accounts = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PARTY_ID")
	private List<Address> addresses = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PARTY_ID")
	private List<Relation> relations = null;

	@Column(name = "IS_ENABLED")
	private Boolean enabled = null;

	/**
	 * @return the partyId
	 */
	public Long getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId
	 *            the partyId to set
	 */
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return the partyCode
	 */
	public String getPartyCode() {
		return partyCode;
	}

	/**
	 * @param partyCode
	 *            the partyCode to set
	 */
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber
	 *            the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName
	 *            the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
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
	 * @return the relations
	 */
	public List<Relation> getRelations() {
		return relations;
	}

	/**
	 * @param relations
	 *            the relations to set
	 */
	public void setRelations(List<Relation> relations) {
		this.relations = relations;
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
	 * get party type code
	 * 
	 * @return
	 * @see PartyType
	 */
	public abstract String getPartyTypeCode();
}
