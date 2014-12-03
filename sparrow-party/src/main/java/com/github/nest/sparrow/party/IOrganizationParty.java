/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;
import java.util.List;

/**
 * organization party interface
 * 
 * @author brad.wu
 */
public interface IOrganizationParty extends IParty {
	/**
	 * get registration date
	 * 
	 * @return
	 */
	Date getRegisterDate();

	/**
	 * get artificial person(法人代表)
	 * 
	 * @return
	 */
	IIndividualParty getArtificialPerson();

	/**
	 * get contact persons
	 * 
	 * @return
	 */
	List<IIndividualParty> getContactPersons();

	/**
	 * get default contact person
	 * 
	 * @return
	 */
	IIndividualParty getDefaultContactPerson();
}
