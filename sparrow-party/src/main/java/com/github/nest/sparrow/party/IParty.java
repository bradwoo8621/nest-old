/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;
import java.util.List;

import com.github.nest.goose.operate.OperateLog;

/**
 * party interface
 * 
 * @author brad.wu
 */
public interface IParty extends Serializable {
	/**
	 * get identity of party. Usually, identity is unique.
	 * 
	 * @return
	 */
	Long getPartyId();

	/**
	 * set id
	 * 
	 * @param id
	 */
	void setPartyId(Long id);

	/**
	 * get name of party
	 * 
	 * @return
	 */
	String getPartyName();

	/**
	 * set name
	 * 
	 * @param name
	 */
	void setPartyName(String name);

	/**
	 * get code of party. code is unique in whole system.
	 * 
	 * @return
	 */
	String getPartyCode();

	/**
	 * set code
	 * 
	 * @param code
	 */
	void setPartyCode(String code);

	/**
	 * get addresses of party
	 * 
	 * @return
	 */
	List<IAddress> getPartyAddresses();

	/**
	 * set address
	 * 
	 * @param addresses
	 */
	void setPartyAddresses(List<IAddress> addresses);

	/**
	 * is party enabled
	 * 
	 * @return
	 */
	Boolean isPartyEnabled();

	/**
	 * set party enabled
	 * 
	 * @param enabled
	 */
	void setPartyEnabled(Boolean enabled);

	/**
	 * get operate log
	 * 
	 * @return
	 */
	OperateLog getPartyOperateLog();

	/**
	 * set operate log
	 * 
	 * @param operateLog
	 */
	void setPartyOperateLog(OperateLog operateLog);

	/**
	 * get optimistic lock
	 * 
	 * @return
	 */
	Long getPartyOptimisticLock();

	/**
	 * set optimistic lock
	 * 
	 * @param optimisticLock
	 */
	void setPartyOptimisticLock(Long optimisticLock);
}
