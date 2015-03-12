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
	 * get name of party
	 * 
	 * @return
	 */
	String getName();

	/**
	 * get code of party. code is unique in whole system.
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * get addresses of party
	 * 
	 * @return
	 */
	List<IAddress> getAddresses();

	/**
	 * get operate log
	 * 
	 * @return
	 */
	OperateLog getOperateLog();

	/**
	 * get optimistic lock
	 * 
	 * @return
	 */
	Long getOptimisticLock();
}
