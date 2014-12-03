/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;

import com.github.nest.sparrow.enums.define.IBank;

/**
 * account interface
 * 
 * @author brad.wu
 */
public interface IAccount {
	/**
	 * get bank
	 * 
	 * @return
	 */
	IBank getBank();

	/**
	 * get account number
	 * 
	 * @return
	 */
	String getAccountNumber();

	/**
	 * get card holder
	 * 
	 * @return
	 */
	IParty getCardHolder();

	/**
	 * get expired date
	 * 
	 * @return
	 */
	Date getExpiredDate();
}
