/**
 * 
 */
package com.github.nest.quelea.model;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.Country;
import com.github.nest.quelea.codes.Gender;
import com.github.nest.quelea.codes.IndividualIdType;
import com.github.nest.quelea.codes.MaritalStatus;
import com.github.nest.quelea.codes.Occupation;
import com.github.nest.quelea.codes.Race;
import com.github.nest.quelea.codes.Religion;

/**
 * individual party
 * 
 * @author brad.wu
 */
public interface IIndividual extends IParty {
	/**
	 * get first name
	 * 
	 * @return
	 */
	String getFirstName();

	/**
	 * set first name
	 * 
	 * @param firstName
	 */
	void setFirstName(String firstName);

	/**
	 * get middle name
	 * 
	 * @return
	 */
	String getMiddleName();

	/**
	 * set middle name
	 * 
	 * @param middleName
	 */
	void setMiddleName(String middleName);

	/**
	 * get last name
	 * 
	 * @return
	 */
	String getLastName();

	/**
	 * set last name
	 * 
	 * @param lastName
	 */
	void setLastName(String lastName);

	/**
	 * get alias name
	 * 
	 * @return
	 */
	String getAlias();

	/**
	 * set alias name
	 * 
	 * @param alias
	 */
	void setAlias(String alias);

	/**
	 * get date of birth
	 * 
	 * @return
	 */
	DateTime getDateOfBirth();

	/**
	 * set date of birth
	 * 
	 * @param dateOfBirth
	 */
	void setDateOfBirth(DateTime dateOfBirth);

	/**
	 * get date of death
	 * 
	 * @return
	 */
	DateTime getDateOfDeath();

	/**
	 * set date of death
	 * 
	 * @param dateOfDeath
	 */
	void setDateOfDeath(DateTime dateOfDeath);

	/**
	 * get country code of birth
	 * 
	 * @return
	 * @see Country
	 */
	String getCountryCodeOfBirth();

	/**
	 * set country code of birth
	 * 
	 * @param countryCodeOfBirth
	 * @see Country
	 */
	void setCountryCodeOfBirth(String countryCodeOfBirth);

	/**
	 * get nationality code
	 * 
	 * @return
	 * @see Country
	 */
	String getNationalityCode();

	/**
	 * set nationality code
	 * 
	 * @param nationalityCode
	 * @see Country
	 */
	void setNationalityCode(String nationalityCode);

	/**
	 * get gender code
	 * 
	 * @return
	 * @see Gender
	 */
	String getGenderCode();

	/**
	 * set gender code
	 * 
	 * @param genderCode
	 * @see Gender
	 */
	void setGenderCode(String genderCode);

	/**
	 * get courtesy title
	 * 
	 * @return
	 */
	String getCourtesyTitle();

	/**
	 * set courtesy title
	 * 
	 * @param courtesyTitle
	 */
	void setCourtesyTitle(String courtesyTitle);

	/**
	 * get id type code
	 * 
	 * @return
	 * @see IndividualIdType
	 */
	String getIdTypeCode();

	/**
	 * set id type code
	 * 
	 * @param idTypeCode
	 * @see IndividualIdType
	 */
	void setIdTypeCode(String idTypeCode);

	/**
	 * get marital status code
	 * 
	 * @return
	 * @see MaritalStatus
	 */
	String getMaritalCode();

	/**
	 * set marital status code
	 * 
	 * @param maritalCode
	 * @see MaritalStatus
	 */
	void setMaritalCode(String maritalCode);

	/**
	 * get race code
	 * 
	 * @return
	 * @see Race
	 */
	String getRaceCode();

	/**
	 * set race code
	 * 
	 * @param raceCode
	 * @see Race
	 */
	void setRaceCode(String raceCode);

	/**
	 * get religion code
	 * 
	 * @return
	 * @see Religion
	 */
	String getReligionCode();

	/**
	 * set religion code
	 * 
	 * @param religionCode
	 * @see Religion
	 */
	void setReligionCode(String religionCode);

	/**
	 * get occupation code
	 * 
	 * @return
	 * @see Occupation
	 */
	String getOccupationCode();

	/**
	 * set occupation code
	 * 
	 * @param occupationCode
	 * @see Occupation
	 */
	void setOccupationCode(String occupationCode);
}
