/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.nest.quelea.codes.AccountType;
import com.github.nest.quelea.codes.CreditCardType;

/**
 * party account
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_PARTY_ACCOUNT")
@SequenceGenerator(name = "S_PARTY_ACCOUNT", sequenceName = "S_PARTY_ACCOUNT")
public class Account implements Serializable {
	private static final long serialVersionUID = -5485671185422131975L;

	@Id
	@Column(name = "ACCOUNT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PARTY_ACCOUNT")
	private Long accountId = null;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber = null;

	@Column(name = "ACCOUNT_TYPE_CODE")
	private String accountTypeCode = null;

	// TODO ROLE CODE
	@Column(name = "BANK_CODE")
	private String bankCode = null;

	@Column(name = "CARD_SECURITY_NUMBER")
	private String cardSecurityNumber = null;

	@Column(name = "CREDIT_CARD_TYPE_CODE")
	private String creditCardTypeCode = null;

	@Column(name = "EXPIRY_DATE")
	@Temporal(TemporalType.DATE)
	private Date expiryDate = null;

	@Column(name = "HOLDER_NAME")
	private String holderName = null;

	@Column(name = "IS_CREDIT_CARD")
	private Boolean creditCard = null;

	@Column(name = "IS_VALID")
	private Boolean valid = null;

	@ManyToOne
	@JoinColumn(name = "PARTY_ID", nullable = false)
	private Party party = null;

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the accountTypeCode
	 * @see AccountType
	 */
	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	/**
	 * @param accountTypeCode
	 *            the accountTypeCode to set
	 * @see AccountType
	 */
	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	/**
	 * @return the bankCode
	 * @see Bank
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode
	 *            the bankCode to set
	 * @see Bank
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the cardSecurityNumber
	 */
	public String getCardSecurityNumber() {
		return cardSecurityNumber;
	}

	/**
	 * @param cardSecurityNumber
	 *            the cardSecurityNumber to set
	 */
	public void setCardSecurityNumber(String cardSecurityNumber) {
		this.cardSecurityNumber = cardSecurityNumber;
	}

	/**
	 * @return the creditCardTypeCode
	 * @see CreditCardType
	 */
	public String getCreditCardTypeCode() {
		return creditCardTypeCode;
	}

	/**
	 * @param creditCardTypeCode
	 *            the creditCardTypeCode to set
	 * @see CreditCardType
	 */
	public void setCreditCardTypeCode(String creditCardTypeCode) {
		this.creditCardTypeCode = creditCardTypeCode;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the holderName
	 */
	public String getHolderName() {
		return holderName;
	}

	/**
	 * @param holderName
	 *            the holderName to set
	 */
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	/**
	 * @return the creditCard
	 */
	public Boolean getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard
	 *            the creditCard to set
	 */
	public void setCreditCard(Boolean creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
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
