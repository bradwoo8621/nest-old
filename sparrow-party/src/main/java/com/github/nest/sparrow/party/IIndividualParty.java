/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;
import java.util.List;

import com.github.nest.sparrow.enums.define.IGender;

/**
 * individual party interface
 * 
 * @author brad.wu
 */
public interface IIndividualParty extends IParty {
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
	 * get last name
	 * 
	 * @return
	 */
	String getLastName();

	/**
	 * get middle name
	 * 
	 * @return
	 */
	String getMiddleName();

	/**
	 * get age
	 * 
	 * @return
	 */
	Integer getAge();

	/**
	 * get birthday
	 * 
	 * @return
	 */
	Date getBirthday();

	/**
	 * get work experiences
	 * 
	 * @return
	 */
	List<IWorkExperience> getWorkExperiences();

	/**
	 * get current work experience
	 * 
	 * @return
	 */
	IWorkExperience getCurrentWorkExperience();
}
