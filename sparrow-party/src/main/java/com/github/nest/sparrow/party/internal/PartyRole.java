/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.IParty;
import com.github.nest.sparrow.party.IPartyRole;

/**
 * abstract party role
 * 
 * @author brad.wu
 */
public abstract class PartyRole implements IPartyRole {
	private static final long serialVersionUID = -5588606244058199338L;

	private Long roleId = null;
	private String roleCode = null;
	private Boolean roleEnabled = Boolean.TRUE;
	private IParty party = null;
	private OperateLog roleOperateLog = null;
	private Long roleOptimisticLock = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleId()
	 */
	@Override
	public Long getRoleId() {
		return this.roleId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleId(java.lang.Long)
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleCode()
	 */
	@Override
	public String getRoleCode() {
		return this.roleCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleCode(java.lang.String)
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#isRoleEnabled()
	 */
	@Override
	public Boolean isRoleEnabled() {
		return this.roleEnabled;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleEnabled(java.lang.Boolean)
	 */
	public void setRoleEnabled(Boolean enabled) {
		this.roleEnabled = enabled;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleOperateLog()
	 */
	@Override
	public OperateLog getRoleOperateLog() {
		return this.roleOperateLog;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleOperateLog(com.github.nest.goose.operate.OperateLog)
	 */
	@Override
	public void setRoleOperateLog(OperateLog operateLog) {
		this.roleOperateLog = operateLog;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleOptimisticLock()
	 */
	@Override
	public Long getRoleOptimisticLock() {
		return this.roleOptimisticLock;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleOptimisticLock(java.lang.Long)
	 */
	@Override
	public void setRoleOptimisticLock(Long optimisticLock) {
		this.roleOptimisticLock = optimisticLock;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getParty()
	 */
	@Override
	public IParty getParty() {
		return this.party;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setParty(com.github.nest.sparrow.party.IParty)
	 */
	@Override
	public void setParty(IParty party) {
		this.party = party;
	}
}
