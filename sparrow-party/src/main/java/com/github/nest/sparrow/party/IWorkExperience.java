/**
 * 
 */
package com.github.nest.sparrow.party;

import java.util.Date;

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
	IOrganization getEmployer();

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
	 * get occupation
	 * 
	 * @return
	 */
	IOccupation getOccupation();

	/**
	 * get job title
	 * 
	 * @return
	 */
	String getJobTitle();
}
