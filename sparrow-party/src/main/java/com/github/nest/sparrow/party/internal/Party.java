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
	private Long partyOptimisticLock = null;

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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setId(java.lang.Long)
	 */
	@Override
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setName(java.lang.String)
	 */
	@Override
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setCode(java.lang.String)
	 */
	@Override
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setAddresses(java.util.List)
	 */
	@Override
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setOperateLog(com.github.nest.goose.operate.OperateLog)
	 */
	@Override
	public void setOperateLog(OperateLog operateLog) {
		this.operateLog = operateLog;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyOptimisticLock()
	 */
	@Override
	public Long getPartyOptimisticLock() {
		return this.partyOptimisticLock;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyOptimisticLock(java.lang.Long)
	 */
	@Override
	public void setPartyOptimisticLock(Long optimisticLock) {
		this.partyOptimisticLock = optimisticLock;
	}
}
