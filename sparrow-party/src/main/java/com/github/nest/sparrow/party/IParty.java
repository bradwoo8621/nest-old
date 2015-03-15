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
	Long getId();

	/**
	 * set id
	 * 
	 * @param id
	 */
	void setId(Long id);

	/**
	 * get party type
	 * 
	 * @return
	 */
	IPartyType getType();

	/**
	 * set party type
	 * 
	 * @param type
	 */
	void setType(IPartyType type);

	/**
	 * get name of party
	 * 
	 * @return
	 */
	String getName();

	/**
	 * set name
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * get code of party. code is unique in whole system.
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * set code
	 * 
	 * @param code
	 */
	void setCode(String code);

	/**
	 * get addresses of party
	 * 
	 * @return
	 */
	List<IAddress> getAddresses();

	/**
	 * set address
	 * 
	 * @param addresses
	 */
	void setAddresses(List<IAddress> addresses);

	/**
	 * get operate log
	 * 
	 * @return
	 */
	OperateLog getOperateLog();

	/**
	 * set operate log
	 * 
	 * @param operateLog
	 */
	void setOperateLog(OperateLog operateLog);

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
