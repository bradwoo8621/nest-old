/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.Country;
import com.github.nest.quelea.codes.Gender;
import com.github.nest.quelea.codes.IndividualIdType;
import com.github.nest.quelea.codes.MaritalStatus;
import com.github.nest.quelea.codes.Occupation;
import com.github.nest.quelea.codes.PartyType;
import com.github.nest.quelea.codes.Race;
import com.github.nest.quelea.codes.Religion;

/**
 * individual party
 * 
 * @author brad.wu
 */
@Entity
@DiscriminatorValue(PartyType.INDIVIDUAL)
public class Individual extends Party implements Serializable {
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the countryCodeOfBirth
	 * @see Country
	 */
	public String getCountryCodeOfBirth() {
		return countryCodeOfBirth;
	}

	/**
	 * @param countryCodeOfBirth
	 *            the countryCodeOfBirth to set
	 * @see Country
	 */
	public void setCountryCodeOfBirth(String countryCodeOfBirth) {
		this.countryCodeOfBirth = countryCodeOfBirth;
	}

	/**
	 * @return the courtesyTitle
	 */
	public String getCourtesyTitle() {
		return courtesyTitle;
	}

	/**
	 * @param courtesyTitle
	 *            the courtesyTitle to set
	 */
	public void setCourtesyTitle(String courtesyTitle) {
		this.courtesyTitle = courtesyTitle;
	}

	/**
	 * @return the dateOfBirth
	 */
	public DateTime getDateOfBirth() {
		return dateOfBirth == null ? null : new DateTime(this.dateOfBirth);
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = null == dateOfBirth ? null : dateOfBirth.toDate();
	}

	/**
	 * @return the dateOfDeath
	 */
	public DateTime getDateOfDeath() {
		return dateOfDeath == null ? null : new DateTime(this.dateOfDeath);
	}

	/**
	 * @param dateOfDeath
	 *            the dateOfDeath to set
	 */
	public void setDateOfDeath(DateTime dateOfDeath) {
		this.dateOfDeath = null == dateOfDeath ? null : dateOfDeath.toDate();
	}

	/**
	 * @return the genderCode
	 * @see Gender
	 */
	public String getGenderCode() {
		return genderCode;
	}

	/**
	 * @param genderCode
	 *            the genderCode to set
	 * @see Gender
	 */
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	/**
	 * @return the idTypeCode
	 * @see IndividualIdType
	 */
	public String getIdTypeCode() {
		return idTypeCode;
	}

	/**
	 * @param idTypeCode
	 *            the idTypeCode to set
	 * @see IndividualIdType
	 */
	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}

	/**
	 * @return the nationalityCode
	 * @see Country
	 */
	public String getNationalityCode() {
		return nationalityCode;
	}

	/**
	 * @param nationalityCode
	 *            the nationalityCode to set
	 * @see Country
	 */
	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	/**
	 * @return the occupationCode
	 * @See Occupation
	 */
	public String getOccupationCode() {
		return occupationCode;
	}

	/**
	 * @param occupationCode
	 *            the occupationCode to set
	 * @see Occupation
	 */
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}

	/**
	 * @return the raceCode
	 * @see Race
	 */
	public String getRaceCode() {
		return raceCode;
	}

	/**
	 * @param raceCode
	 *            the raceCode to set
	 * @see Race
	 */
	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	/**
	 * @return the religionCode
	 * @see Religion
	 */
	public String getReligionCode() {
		return religionCode;
	}

	/**
	 * @param religionCode
	 *            the religionCode to set
	 * @see Religion
	 */
	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
	}

	/**
	 * @return the maritalCode
	 * @see MaritalStatus
	 */
	public String getMaritalCode() {
		return maritalCode;
	}

	/**
	 * @param maritalCode
	 *            the maritalCode to set
	 * @see MaritalStatus
	 */
	public void setMaritalCode(String maritalCode) {
		this.maritalCode = maritalCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.Party#getPartyTypeCode()
	 */
	@Override
	public String getPartyTypeCode() {
		return PartyType.INDIVIDUAL;
	}
}
