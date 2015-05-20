/**
 * 
 */
package com.github.nest.quelea.internal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.PartyType;
import com.github.nest.quelea.model.IIndividual;

/**
 * individual party
 * 
 * @author brad.wu
 */
@Entity
@DiscriminatorValue(PartyType.INDIVIDUAL)
public class Individual extends Party implements IIndividual {
	private static final long serialVersionUID = -1427033874602578931L;

	@Column(name = "FIRST_NAME")
	private String firstName = null;

	@Column(name = "MIDDLE_NAME")
	private String middleName = null;

	@Column(name = "LAST_NAME")
	private String lastName = null;

	@Column(name = "ALIAS_NAME")
	private String alias = null;

	@Column(name = "COUNTRY_CODE_OF_BIRTH")
	private String countryCodeOfBirth = null;

	@Column(name = "COURTESY_TITLE")
	private String courtesyTitle = null;

	@Column(name = "DATE_OF_BIRTH")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth = null;

	@Column(name = "DATE_OF_DEATH")
	@Temporal(TemporalType.DATE)
	private Date dateOfDeath = null;

	@Column(name = "GENDER_CODE")
	private String genderCode = null;

	@Column(name = "ID_TYPE_CODE")
	private String idTypeCode = null;

	@Column(name = "NATIONALITY_CODE")
	private String nationalityCode = null;

	@Column(name = "OCCUPATION_CODE")
	private String occupationCode = null;

	@Column(name = "RACE_CODE")
	private String raceCode = null;

	@Column(name = "RELIGION_CODE")
	private String religionCode = null;

	@Column(name = "MARITAL_CODE")
	private String maritalCode = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getMiddleName()
	 */
	@Override
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setMiddleName(java.lang.String)
	 */
	@Override
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getLastName()
	 */
	@Override
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getAlias()
	 */
	@Override
	public String getAlias() {
		return this.alias;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setAlias(java.lang.String)
	 */
	@Override
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getDateOfBirth()
	 */
	@Override
	public DateTime getDateOfBirth() {
		return this.dateOfBirth == null ? null : new DateTime(this.dateOfBirth);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setDateOfBirth(org.joda.time.DateTime)
	 */
	@Override
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = null == dateOfBirth ? null : dateOfBirth.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getDateOfDeath()
	 */
	@Override
	public DateTime getDateOfDeath() {
		return this.dateOfDeath == null ? null : new DateTime(this.dateOfDeath);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setDateOfDeath(org.joda.time.DateTime)
	 */
	@Override
	public void setDateOfDeath(DateTime dateOfDeath) {
		this.dateOfDeath = null == dateOfDeath ? null : dateOfDeath.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getCountryCodeOfBirth()
	 */
	@Override
	public String getCountryCodeOfBirth() {
		return this.countryCodeOfBirth;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setCountryCodeOfBirth(java.lang.String)
	 */
	@Override
	public void setCountryCodeOfBirth(String countryCodeOfBirth) {
		this.countryCodeOfBirth = countryCodeOfBirth;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getNationalityCode()
	 */
	@Override
	public String getNationalityCode() {
		return this.nationalityCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setNationalityCode(java.lang.String)
	 */
	@Override
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getGenderCode()
	 */
	@Override
	public String getGenderCode() {
		return this.genderCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setGenderCode(java.lang.String)
	 */
	@Override
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getCourtesyTitle()
	 */
	@Override
	public String getCourtesyTitle() {
		return this.courtesyTitle;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setCourtesyTitle(java.lang.String)
	 */
	@Override
	public void setCourtesyTitle(String courtesyTitle) {
		this.courtesyTitle = courtesyTitle;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getIdTypeCode()
	 */
	@Override
	public String getIdTypeCode() {
		return this.idTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setIdTypeCode(java.lang.String)
	 */
	@Override
	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getMaritalCode()
	 */
	@Override
	public String getMaritalCode() {
		return this.maritalCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setMaritalCode(java.lang.String)
	 */
	@Override
	public void setMaritalCode(String maritalCode) {
		this.maritalCode = maritalCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getRaceCode()
	 */
	@Override
	public String getRaceCode() {
		return this.raceCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setRaceCode(java.lang.String)
	 */
	@Override
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getReligionCode()
	 */
	@Override
	public String getReligionCode() {
		return this.religionCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setReligionCode(java.lang.String)
	 */
	@Override
	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#getOccupationCode()
	 */
	@Override
	public String getOccupationCode() {
		return this.occupationCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IIndividual#setOccupationCode(java.lang.String)
	 */
	@Override
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.internal.model.Party#getPartyTypeCode()
	 */
	@Override
	public String getPartyTypeCode() {
		return PartyType.INDIVIDUAL;
	}
}
