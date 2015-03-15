/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.github.nest.goose.human.IGender;
import com.github.nest.goose.location.ICountry;
import com.github.nest.sparrow.party.IEducationExperience;
import com.github.nest.sparrow.party.IIndividual;
import com.github.nest.sparrow.party.IPartyType;
import com.github.nest.sparrow.party.IWorkExperience;
import com.github.nest.sparrow.party.internal.codes.PartyType;

/**
 * individual party implementation
 * 
 * @author brad.wu
 */
public abstract class Individual extends Party implements IIndividual {
	private static final long serialVersionUID = -8038640541833998256L;

	private String idNumber = null;
	private IGender gender = null;
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private Date dateOfBirth = null;
	private Date dateOfDeath = null;
	private ICountry bornIn = null;
	private ICountry nationality = null;
	private List<IWorkExperience> workExperiences = null;
	private List<IEducationExperience> educationExperiences = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IParty#getType()
	 */
	@Override
	public IPartyType getType() {
		return PartyType.INDIVIDUAL;
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
	 * @see com.github.nest.sparrow.party.IIndividual#getIdNumber()
	 */
	@Override
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * @param idNumber
	 *            the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getGender()
	 */
	@Override
	public IGender getGender() {
		return this.gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(IGender gender) {
		this.gender = gender;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.internal.Party#getName()
	 */
	@Override
	public String getName() {
		return IndividualNameConcatenator.concat(this.getFirstName(), this.getMiddleName(), this.getLastName());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getLastName()
	 */
	@Override
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getAge()
	 */
	@Override
	public Integer getAge() {
		return this.getAge(new Date());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getAge(java.util.Date)
	 */
	@Override
	public Integer getAge(Date date) {
		LocalDate end = null;
		if (this.getDateOfDeath() != null) {
			end = LocalDate.fromDateFields(this.getDateOfDeath());
		} else {
			end = LocalDate.fromDateFields(date);
		}
		LocalDate start = LocalDate.fromDateFields(this.getDateOfBirth());
		return Years.yearsBetween(start, end).getYears();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getDateOfBirth()
	 */
	@Override
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getDateOfDeath()
	 */
	@Override
	public Date getDateOfDeath() {
		return this.dateOfDeath;
	}

	/**
	 * @param dateOfDeath
	 *            the dateOfDeath to set
	 */
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getBornIn()
	 */
	@Override
	public ICountry getBornIn() {
		return this.bornIn;
	}

	/**
	 * @param bornIn
	 *            the bornIn to set
	 */
	public void setBornIn(ICountry bornIn) {
		this.bornIn = bornIn;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getNationality()
	 */
	@Override
	public ICountry getNationality() {
		return this.nationality;
	}

	/**
	 * @param nationality
	 *            the nationality to set
	 */
	public void setNationality(ICountry nationality) {
		this.nationality = nationality;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getWorkExperiences()
	 */
	@Override
	public List<IWorkExperience> getWorkExperiences() {
		return this.workExperiences;
	}

	/**
	 * @param workExperiences
	 *            the workExperiences to set
	 */
	public void setWorkExperiences(List<IWorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IIndividual#getEducationExperiences()
	 */
	@Override
	public List<IEducationExperience> getEducationExperiences() {
		return this.educationExperiences;
	}

	/**
	 * @param educationExperiences
	 *            the educationExperiences to set
	 */
	public void setEducationExperiences(List<IEducationExperience> educationExperiences) {
		this.educationExperiences = educationExperiences;
	}
}
