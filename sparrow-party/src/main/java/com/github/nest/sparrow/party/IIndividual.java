/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;
import java.util.List;

import com.github.nest.goose.human.IGender;
import com.github.nest.goose.location.ICountry;

/**
 * individual party interface
 * 
 * @author brad.wu
 */
public interface IIndividual extends IParty {
	/**
	 * get id number
	 * 
	 * @return
	 */
	String getIdNumber();

	/**
	 * 
	 * @param idNumber
	 */
	void setIdNumber(String idNumber);

	/**
	 * get gender
	 * 
	 * @return
	 */
	IGender getGender();

	/**
	 * 
	 * @param gender
	 */
	void setGender(IGender gender);

	/**
	 * get first name
	 * 
	 * @return
	 */
	String getFirstName();

	/**
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
	 * 
	 * @param lastName
	 */
	void setLastName(String lastName);

	/**
	 * get age
	 * 
	 * @return
	 */
	Integer getAge();

	/**
	 * get age by given date
	 * 
	 * @param date
	 * @return
	 */
	Integer getAge(Date date);

	/**
	 * get birthday
	 * 
	 * @return
	 */
	Date getDateOfBirth();

	/**
	 * 
	 * @param dateOfBirth
	 */
	void setDateOfBirth(Date dateOfBirth);

	/**
	 * get date of death
	 * 
	 * @return
	 */
	Date getDateOfDeath();

	/**
	 * 
	 * @param dateOfDeath
	 */
	void setDateOfDeath(Date dateOfDeath);

	/**
	 * get born in country
	 * 
	 * @return
	 */
	ICountry getBornIn();

	/**
	 * 
	 * @param bornIn
	 */
	void setBornIn(ICountry bornIn);

	/**
	 * get nationality
	 * 
	 * @return
	 */
	ICountry getNationality();

	/**
	 * 
	 * @param nationality
	 */
	void setNationality(ICountry nationality);

	/**
	 * get work experiences
	 * 
	 * @return
	 */
	List<IWorkExperience> getWorkExperiences();

	/**
	 * 
	 * @param workExperiences
	 */
	void setWorkExperiences(List<IWorkExperience> workExperiences);

	/**
	 * get educations experiences
	 * 
	 * @return
	 */
	List<IEducationExperience> getEducationExperiences();

	/**
	 * 
	 * @param educationExperiences
	 */
	void setEducationExperiences(List<IEducationExperience> educationExperiences);
}
