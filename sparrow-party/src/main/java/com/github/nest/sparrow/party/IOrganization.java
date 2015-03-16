/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;

import com.github.nest.goose.location.ICountry;
import com.github.nest.sparrow.party.codes.IIndustry;

/**
 * organization party interface
 * 
 * @author brad.wu
 */
public interface IOrganization extends IParty {
	/**
	 * get register number
	 * 
	 * @return
	 */
	String getRegisterNumber();

	/**
	 * 
	 * @param registerNumber
	 */
	void setRegisterNumber(String registerNumber);

	/**
	 * get registration date
	 * 
	 * @return
	 */
	Date getRegisterDate();

	/**
	 * 
	 * @param registerDate
	 */
	void setRegisterDate(Date registerDate);

	/**
	 * get close down date
	 * 
	 * @return
	 */
	Date getCloseDownDate();

	/**
	 * 
	 * @param closeDownDate
	 */
	void setCloseDownDate(Date closeDownDate);

	/**
	 * get register in country
	 * 
	 * @return
	 */
	ICountry getRegisterIn();

	/**
	 * 
	 * @param registerIn
	 */
	void setRegisterIn(ICountry registerIn);

	/**
	 * get industry
	 * 
	 * @return
	 */
	IIndustry getIndustry();

	/**
	 * 
	 * @param industry
	 */
	void setIndustry(IIndustry industry);

	/**
	 * get artificial person(法人代表)
	 * 
	 * @return
	 */
	IIndividual getArtificialPerson();

	/**
	 * 
	 * @param artificialPerson
	 */
	void setArtificialPerson(IIndividual artificialPerson);
}
