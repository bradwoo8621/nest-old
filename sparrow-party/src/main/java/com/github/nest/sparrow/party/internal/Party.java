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
public class Party implements IParty {
	private static final long serialVersionUID = 7917826523152991021L;

	private Long id = null;
	private String name = null;
	private String code = null;
	private List<IAddress> addresses = null;
	private OperateLog operateLog = null;
	private Long partyOptimisticLock = null;
	private Boolean partyEnabled = Boolean.TRUE;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyId()
	 */
	@Override
	public Long getPartyId() {
		return this.id;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyId(java.lang.Long)
	 */
	@Override
	public void setPartyId(Long id) {
		this.id = id;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyName()
	 */
	@Override
	public String getPartyName() {
		return this.name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyName(java.lang.String)
	 */
	@Override
	public void setPartyName(String name) {
		this.name = name;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyCode()
	 */
	@Override
	public String getPartyCode() {
		return this.code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyCode(java.lang.String)
	 */
	@Override
	public void setPartyCode(String code) {
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyAddresses()
	 */
	@Override
	public List<IAddress> getPartyAddresses() {
		return this.addresses;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyAddresses(java.util.List)
	 */
	@Override
	public void setPartyAddresses(List<IAddress> addresses) {
		this.addresses = addresses;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyOperateLog()
	 */
	@Override
	public OperateLog getPartyOperateLog() {
		return this.operateLog;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyOperateLog(com.github.nest.goose.operate.OperateLog)
	 */
	@Override
	public void setPartyOperateLog(OperateLog operateLog) {
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#isPartyEnabled()
	 */
	@Override
	public Boolean isPartyEnabled() {
		return this.partyEnabled;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyEnabled(java.lang.Boolean)
	 */
	@Override
	public void setPartyEnabled(Boolean enabled) {
		this.partyEnabled = enabled;
	}
}
