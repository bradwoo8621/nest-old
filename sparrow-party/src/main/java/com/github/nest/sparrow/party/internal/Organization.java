/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;

import com.github.nest.goose.location.ICountry;
import com.github.nest.sparrow.party.IIndividual;
import com.github.nest.sparrow.party.IIndustry;
import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyType;
import com.github.nest.sparrow.party.internal.codes.PartyType;

/**
 * organization party implementation
 * 
 * @author brad.wu
 */
public abstract class Organization extends Party implements IOrganization {
	private static final long serialVersionUID = -3147164186617820585L;
	private String registerNumber = null;
	private Date registerDate = null;
	private Date closeDownDate = null;
	private ICountry registerIn = null;
	private IIndustry industry = null;
	private IIndividual artificialPerson = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getType()
	 */
	@Override
	public IPartyType getType() {
		return PartyType.ORGANIZATION;
	}

	/**
	 * do nothing
	 * 
	 * @see com.github.nest.sparrow.party.IParty#setType(com.github.nest.sparrow.party.IPartyType)
	 */
	@Override
	public void setType(IPartyType type) {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterNumber()
	 */
	@Override
	public String getRegisterNumber() {
		return this.registerNumber;
	}

	/**
	 * @param registerNumber
	 *            the registerNumber to set
	 */
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterDate()
	 */
	@Override
	public Date getRegisterDate() {
		return this.registerDate;
	}

	/**
	 * @param registerDate
	 *            the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getCloseDownDate()
	 */
	@Override
	public Date getCloseDownDate() {
		return this.closeDownDate;
	}

	/**
	 * @param closeDownDate
	 *            the closeDownDate to set
	 */
	public void setCloseDownDate(Date closeDownDate) {
		this.closeDownDate = closeDownDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterIn()
	 */
	@Override
	public ICountry getRegisterIn() {
		return this.registerIn;
	}

	/**
	 * @param registerIn
	 *            the registerIn to set
	 */
	public void setRegisterIn(ICountry registerIn) {
		this.registerIn = registerIn;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getIndustry()
	 */
	@Override
	public IIndustry getIndustry() {
		return this.industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(IIndustry industry) {
		this.industry = industry;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#getArtificialPerson()
	 */
	@Override
	public IIndividual getArtificialPerson() {
		return this.artificialPerson;
	}

	/**
	 * @param artificialPerson
	 *            the artificialPerson to set
	 */
	public void setArtificialPerson(IIndividual artificialPerson) {
		this.artificialPerson = artificialPerson;
	}
}
