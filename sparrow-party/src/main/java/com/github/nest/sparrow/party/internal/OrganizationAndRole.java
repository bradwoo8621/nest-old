/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;
import java.util.List;

import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.IAddress;
import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IParty;
import com.github.nest.sparrow.party.PartyException;
import com.github.nest.sparrow.party.codes.IIndustry;

/**
 * organization party and role interface.<br>
 * for implement the concrete party role object, since multiple extension is not
 * permitted in java.
 * 
 * @author brad.wu
 */
public abstract class OrganizationAndRole extends PartyRole implements IOrganization {
	private static final long serialVersionUID = 5127074376660325058L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getParty()
	 */
	@Override
	public IOrganization getParty() {
		return (IOrganization) super.getParty();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setParty(com.github.nest.sparrow.party.IParty)
	 */
	@Override
	public void setParty(IParty party) {
		assert party != null && party instanceof IOrganization : "Party must be an instance of ["
				+ IOrganization.class.getName() + "].";

		super.setParty(party);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyId()
	 */
	@Override
	public Long getPartyId() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getPartyId();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyId(java.lang.Long)
	 */
	@Override
	public void setPartyId(Long id) {
		checkParty().setPartyId(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyName()
	 */
	@Override
	public String getPartyName() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getPartyName();
	}

	/**
	 * do nothing
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyName(java.lang.String)
	 */
	@Override
	public void setPartyName(String name) {
		checkParty().setPartyName(name);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyCode()
	 */
	@Override
	public String getPartyCode() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getPartyCode();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyCode(java.lang.String)
	 */
	@Override
	public void setPartyCode(String code) {
		checkParty().setPartyCode(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyAddresses()
	 */
	@Override
	public List<IAddress> getPartyAddresses() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getPartyAddresses();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyAddresses(java.util.List)
	 */
	@Override
	public void setPartyAddresses(List<IAddress> addresses) {
		checkParty().setPartyAddresses(addresses);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#isPartyEnabled()
	 */
	@Override
	public Boolean isPartyEnabled() {
		IOrganization party = this.getParty();
		return party == null ? null : party.isPartyEnabled();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyEnabled(java.lang.Boolean)
	 */
	@Override
	public void setPartyEnabled(Boolean enabled) {
		checkParty().setPartyEnabled(enabled);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterNumber()
	 */
	@Override
	public String getRegisterNumber() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getRegisterNumber();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setRegisterNumber(java.lang.String)
	 */
	@Override
	public void setRegisterNumber(String registerNumber) {
		checkParty().setRegisterNumber(registerNumber);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterDate()
	 */
	@Override
	public Date getRegisterDate() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getRegisterDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setRegisterDate(java.util.Date)
	 */
	@Override
	public void setRegisterDate(Date registerDate) {
		checkParty().setRegisterDate(registerDate);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getCloseDownDate()
	 */
	@Override
	public Date getCloseDownDate() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getCloseDownDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setCloseDownDate(java.util.Date)
	 */
	@Override
	public void setCloseDownDate(Date closeDownDate) {
		checkParty().setCloseDownDate(closeDownDate);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterIn()
	 */
	@Override
	public ICountry getRegisterIn() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getRegisterIn();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setRegisterIn(com.github.nest.goose.location.ICountry)
	 */
	@Override
	public void setRegisterIn(ICountry registerIn) {
		checkParty().setRegisterIn(registerIn);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getIndustry()
	 */
	@Override
	public IIndustry getIndustry() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getIndustry();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setIndustry(com.github.nest.sparrow.party.codes.IIndustry)
	 */
	@Override
	public void setIndustry(IIndustry industry) {
		checkParty().setIndustry(industry);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyOperateLog()
	 */
	@Override
	public OperateLog getPartyOperateLog() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getPartyOperateLog();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyOperateLog(com.github.nest.goose.operate.OperateLog)
	 */
	@Override
	public void setPartyOperateLog(OperateLog operateLog) {
		checkParty().setPartyOperateLog(operateLog);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyOptimisticLock()
	 */
	@Override
	public Long getPartyOptimisticLock() {
		IOrganization party = this.getParty();
		return party == null ? null : party.getPartyOptimisticLock();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyOptimisticLock(java.lang.Long)
	 */
	@Override
	public void setPartyOptimisticLock(Long optimisticLock) {
		checkParty().setPartyOptimisticLock(optimisticLock);
	}

	/**
	 * check party
	 */
	protected IOrganization checkParty() {
		IOrganization party = getParty();
		if (party == null) {
			throw new PartyException("Party must be set.");
		} else {
			return party;
		}
	}
}
