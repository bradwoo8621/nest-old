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
	 * get gender
	 * 
	 * @return
	 */
	IGender getGender();

	/**
	 * get first name
	 * 
	 * @return
	 */
	String getFirstName();

	/**
	 * get middle name
	 * 
	 * @return
	 */
	String getMiddleName();

	/**
	 * get last name
	 * 
	 * @return
	 */
	String getLastName();

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
	 * get date of death
	 * 
	 * @return
	 */
	Date getDateOfDeath();

	/**
	 * get born in country
	 * 
	 * @return
	 */
	ICountry getBornIn();

	/**
	 * get nationality
	 * 
	 * @return
	 */
	ICountry getNationality();

	/**
	 * get work experiences
	 * 
	 * @return
	 */
	List<IWorkExperience> getWorkExperiences();

	/**
	 * get educations experiences
	 * 
	 * @return
	 */
	List<IEducationExperience> getEducationExperiences();
}
