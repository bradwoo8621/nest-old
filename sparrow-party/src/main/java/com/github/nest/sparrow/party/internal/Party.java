/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.List;

import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.IAddress;
import com.github.nest.sparrow.party.IParty;

/**
 * party implementation
 * 
 * @author brad.wu
 */
public abstract class Party implements IParty {
	private static final long serialVersionUID = 7917826523152991021L;

	private Long id = null;
	private String name = null;
	private String code = null;
	private List<IAddress> addresses = null;
	private OperateLog operateLog = null;
	private Long optimisticLock = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getAddresses()
	 */
	@Override
	public List<IAddress> getAddresses() {
		return this.addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<IAddress> addresses) {
		this.addresses = addresses;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getOperateLog()
	 */
	@Override
	public OperateLog getOperateLog() {
		return this.operateLog;
	}

	/**
	 * @param operateLog
	 *            the operateLog to set
	 */
	public void setOperateLog(OperateLog operateLog) {
		this.operateLog = operateLog;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getOptimisticLock()
	 */
	@Override
	public Long getOptimisticLock() {
		return this.optimisticLock;
	}

	/**
	 * @param optimisticLock
	 *            the optimisticLock to set
	 */
	public void setOptimisticLock(Long optimisticLock) {
		this.optimisticLock = optimisticLock;
	}
}
