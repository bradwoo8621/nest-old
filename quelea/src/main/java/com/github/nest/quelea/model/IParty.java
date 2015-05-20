/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.List;

import com.github.nest.arteryx.persistent.IAuditable;
import com.github.nest.arteryx.persistent.IVersionable;
import com.github.nest.quelea.codes.PartyType;

/**
 * party interface
 * 
 * @author brad.wu
 */
public interface IParty extends IAuditable, IVersionable, Serializable {
	/**
	 * get party id
	 * 
	 * @return
	 */
	Long getPartyId();

	/**
	 * set party id
	 * 
	 * @param partyId
	 */
	void setPartyId(Long partyId);

	/**
	 * get party type code
	 * 
	 * @return
	 * @see PartyType
	 */
	String getPartyTypeCode();

	/**
	 * get party name
	 * 
	 * @return
	 */
	String getPartyName();

	/**
	 * set party name
	 * 
	 * @param partyName
	 */
	void setPartyName(String partyName);

	/**
	 * get id number
	 * 
	 * @return
	 */
	String getIdNumber();

	/**
	 * set id number
	 * 
	 * @param idNumber
	 */
	void setIdNumber(String idNumber);

	/**
	 * get addresses
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
	 * get accounts.
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
	 * get relations
	 * 
	 * @return
	 */
	List<IRelation> getRelations();

	/**
	 * set relations
	 * 
	 * @param relations
	 */
	void setRelations(List<IRelation> relations);

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
