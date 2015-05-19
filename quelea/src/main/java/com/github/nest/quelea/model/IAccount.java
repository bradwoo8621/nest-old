/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.Date;

import com.github.nest.quelea.codes.AccountType;
import com.github.nest.quelea.codes.CreditCardType;

/**
 * account
 * 
 * @author brad.wu
 */
public interface IAccount extends Serializable {
	/**
	 * get account id
	 * 
	 * @return
	 */
	Long getAccountId();

	/**
	 * set account id
	 * 
	 * @param accountId
	 */
	void setAccountId(Long accountId);

	/**
	 * get account holder name
	 * 
	 * @return
	 */
	String getHolderName();

	/**
	 * set account holder name
	 * 
	 * @param holderName
	 */
	void setHolderName(String holderName);

	/**
	 * get account number
	 * 
	 * @return
	 */
	String getAccountNumber();

	/**
	 * set account number
	 * 
	 * @param accountNumber
	 */
	void setAccountNumber(String accountNumber);

	/**
	 * get bank code
	 * 
	 * @return
	 * @see IBank
	 */
	String getBankCode();

	/**
	 * set bank code
	 * 
	 * @param bankCode
	 * @see IBank
	 */
	void setBankCode(String bankCode);

	/**
	 * is credit card or not
	 * 
	 * @return
	 */
	boolean isCreditCard();

	/**
	 * get credit card type code
	 * 
	 * @return
	 * @see CreditCardType
	 */
	String getCreditCardTypeCode();

	/**
	 * set credit card type code
	 * 
	 * @param creditCardTypeCode
	 * @see CreditCardType
	 */
	void setCreditCardTypeCode(String creditCardTypeCode);

	/**
	 * get account type code
	 * 
	 * @return
	 * @see AccountType
	 */
	String getAccountTypeCode();

	/**
	 * set account type code
	 * 
	 * @param accountTypeCode
	 * @see AccountType
	 */
	void setAccountTypeCode(String accountTypeCode);

	/**
	 * get card security number
	 * 
	 * @return
	 */
	String getCardSecurityNumber();

	/**
	 * set card security number
	 * 
	 * @param cardSecurityNumber
	 */
	void setCardSecurityNumber(String cardSecurityNumber);

	/**
	 * get expiry date
	 * 
	 * @return
	 */
	Date getExpiryDate();

	/**
	 * set expiry date
	 * 
	 * @param expiryDate
	 */
	void setExpiryDate(Date expiryDate);

	/**
	 * is valid
	 * 
	 * @return
	 */
	Boolean isValid();

	/**
	 * set valid status
	 * 
	 * @param valid
	 */
	void setValid(Boolean valid);
}
