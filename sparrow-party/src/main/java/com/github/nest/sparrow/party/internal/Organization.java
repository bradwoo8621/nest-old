/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;

import com.github.nest.goose.location.ICountry;
import com.github.nest.sparrow.party.IIndividual;
import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.codes.IIndustry;

/**
 * organization party implementation
 * 
 * @author brad.wu
 */
public class Organization extends Party implements IOrganization {
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
	 * @see com.github.nest.sparrow.party.IOrganization#getRegisterNumber()
	 */
	@Override
	public String getRegisterNumber() {
		return this.registerNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setRegisterNumber(java.lang.String)
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setRegisterDate(java.util.Date)
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setCloseDownDate(java.util.Date)
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setRegisterIn(com.github.nest.goose.location.ICountry)
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setIndustry(com.github.nest.sparrow.party.codes.IIndustry)
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
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IOrganization#setArtificialPerson(com.github.nest.sparrow.party.IIndividual)
	 */
	public void setArtificialPerson(IIndividual artificialPerson) {
		this.artificialPerson = artificialPerson;
	}
}
