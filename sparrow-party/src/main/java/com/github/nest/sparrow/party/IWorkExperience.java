/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;

import com.github.nest.sparrow.enums.define.IOccupation;

/**
 * work experience interface
 * 
 * @author brad.wu
 */
public interface IWorkExperience {
	/**
	 * get employer
	 * 
	 * @return
	 */
	IOrganizationParty getEmployer();

	/**
	 * get start from
	 * 
	 * @return
	 */
	Date getStart();

	/**
	 * get end to
	 * 
	 * @return
	 */
	Date getEnd();

	/**
	 * get title
	 * 
	 * @return
	 */
	String getTitle();

	/**
	 * get occupation
	 * 
	 * @return
	 */
	IOccupation getOccupation();
}
