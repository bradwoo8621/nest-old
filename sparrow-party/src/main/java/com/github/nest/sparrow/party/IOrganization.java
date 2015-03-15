/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;

import com.github.nest.goose.location.ICountry;

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
	 * get registration date
	 * 
	 * @return
	 */
	Date getRegisterDate();

	/**
	 * get close down date
	 * 
	 * @return
	 */
	Date getCloseDownDate();

	/**
	 * get register in country
	 * 
	 * @return
	 */
	ICountry getRegisterIn();

	/**
	 * get industry
	 * 
	 * @return
	 */
	IIndustry getIndustry();

	/**
	 * get artificial person(法人代表)
	 * 
	 * @return
	 */
	IIndividual getArtificialPerson();
}
