/**
 * 
 */
package com.github.nest.quelea.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.nest.quelea.codes.BankClass;
import com.github.nest.quelea.codes.RoleType;

/**
 * bank
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_BANK")
@DiscriminatorValue(RoleType.BANK)
public class Bank extends PartyRole {
	private static final long serialVersionUID = -789606068847945119L;

	@Column(name = "BANK_TYPE_CODE")
	private String bankTypeCode = null;

	@Column(name = "BANK_CODE")
	private String bankCode = null;

	@Column(name = "DIGIT_CODE")
	private String digitCode = null;

	@Column(name = "BANK_CLASS_CODE")
	private String bankClassCode = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.BANK;
	}

	/**
	 * @return the bankTypeCode
	 * @BankType
	 */
	public String getBankTypeCode() {
		return bankTypeCode;
	}

	/**
	 * @param bankTypeCode
	 *            the bankTypeCode to set
	 * @BankType
	 */
	public void setBankTypeCode(String bankTypeCode) {
		this.bankTypeCode = bankTypeCode;
	}

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode
	 *            the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the digitCode
	 */
	public String getDigitCode() {
		return digitCode;
	}

	/**
	 * @param digitCode
	 *            the digitCode to set
	 */
	public void setDigitCode(String digitCode) {
		this.digitCode = digitCode;
	}

	/**
	 * @return the bankClassCode
	 * @see BankClass
	 */
	public String getBankClassCode() {
		return bankClassCode;
	}

	/**
	 * @param bankClassCode
	 *            the bankClassCode to set
	 * @see BankClass
	 */
	public void setBankClassCode(String bankClassCode) {
		this.bankClassCode = bankClassCode;
	}
}
