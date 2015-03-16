/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;
import java.util.List;

import com.github.nest.goose.human.IGender;
import com.github.nest.goose.location.ICountry;
import com.github.nest.goose.operate.OperateLog;
import com.github.nest.sparrow.party.IAddress;
import com.github.nest.sparrow.party.IEducationExperience;
import com.github.nest.sparrow.party.IIndividual;
import com.github.nest.sparrow.party.IParty;
import com.github.nest.sparrow.party.IWorkExperience;
import com.github.nest.sparrow.party.PartyException;

/**
 * individual party and role interface.<br>
 * for implement the concrete party role object, since multiple extension is not
 * permitted in java.
 * 
 * @author brad.wu
 */
public abstract class IndividualAndRole extends PartyRole implements IIndividual {
	private static final long serialVersionUID = 7099087522087708091L;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getParty()
	 */
	@Override
	public IIndividual getParty() {
		return (IIndividual) super.getParty();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setParty(com.github.nest.sparrow.party.IParty)
	 */
	@Override
	public void setParty(IParty party) {
		assert party != null && party instanceof IIndividual : "Party must be an instance of ["
				+ IIndividual.class.getName() + "].";

		super.setParty(party);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyId()
	 */
	@Override
	public Long getPartyId() {
		IIndividual party = this.getParty();
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
		return IndividualNameConcatenator.concat(this.getFirstName(), this.getMiddleName(), this.getLastName());
	}

	/**
	 * do nothing
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setPartyName(java.lang.String)
	 */
	@Override
	public void setPartyName(String name) {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyCode()
	 */
	@Override
	public String getPartyCode() {
		IIndividual party = this.getParty();
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
		IIndividual party = this.getParty();
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
		IIndividual party = this.getParty();
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
	 * @see com.github.nest.sparrow.party.IIndividual#getIdNumber()
	 */
	@Override
	public String getIdNumber() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getIdNumber();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setIdNumber(java.lang.String)
	 */
	@Override
	public void setIdNumber(String idNumber) {
		checkParty().setIdNumber(idNumber);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getGender()
	 */
	@Override
	public IGender getGender() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getGender();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setGender(com.github.nest.goose.human.IGender)
	 */
	@Override
	public void setGender(IGender gender) {
		checkParty().setGender(gender);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getFirstName()
	 */
	@Override
	public String getFirstName() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getFirstName();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		checkParty().setFirstName(firstName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getMiddleName();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setMiddleName(java.lang.String)
	 */
	@Override
	public void setMiddleName(String middleName) {
		checkParty().setMiddleName(middleName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getLastName()
	 */
	@Override
	public String getLastName() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getLastName();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		checkParty().setLastName(lastName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getAge()
	 */
	@Override
	public Integer getAge() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getAge();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getAge(java.util.Date)
	 */
	@Override
	public Integer getAge(Date date) {
		IIndividual party = this.getParty();
		return party == null ? null : party.getAge(date);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getDateOfBirth()
	 */
	@Override
	public Date getDateOfBirth() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getDateOfBirth();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setDateOfBirth(java.util.Date)
	 */
	@Override
	public void setDateOfBirth(Date dateOfBirth) {
		checkParty().setDateOfBirth(dateOfBirth);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getDateOfDeath()
	 */
	@Override
	public Date getDateOfDeath() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getDateOfDeath();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setDateOfDeath(java.util.Date)
	 */
	@Override
	public void setDateOfDeath(Date dateOfDeath) {
		checkParty().setDateOfDeath(dateOfDeath);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getBornIn()
	 */
	@Override
	public ICountry getBornIn() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getBornIn();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setBornIn(com.github.nest.goose.location.ICountry)
	 */
	@Override
	public void setBornIn(ICountry bornIn) {
		checkParty().setBornIn(bornIn);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getNationality()
	 */
	@Override
	public ICountry getNationality() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getNationality();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setNationality(com.github.nest.goose.location.ICountry)
	 */
	@Override
	public void setNationality(ICountry nationality) {
		checkParty().setNationality(nationality);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getWorkExperiences()
	 */
	@Override
	public List<IWorkExperience> getWorkExperiences() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getWorkExperiences();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setWorkExperiences(java.util.List)
	 */
	@Override
	public void setWorkExperiences(List<IWorkExperience> workExperiences) {
		checkParty().setWorkExperiences(workExperiences);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getEducationExperiences()
	 */
	@Override
	public List<IEducationExperience> getEducationExperiences() {
		IIndividual party = this.getParty();
		return party == null ? null : party.getEducationExperiences();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#setEducationExperiences(java.util.List)
	 */
	@Override
	public void setEducationExperiences(List<IEducationExperience> educationExperiences) {
		checkParty().setEducationExperiences(educationExperiences);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getPartyOperateLog()
	 */
	@Override
	public OperateLog getPartyOperateLog() {
		IIndividual party = this.getParty();
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
		IIndividual party = this.getParty();
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
	protected IIndividual checkParty() {
		IIndividual party = getParty();
		if (party == null) {
			throw new PartyException("Party must be set.");
		} else {
			return party;
		}
	}
}
