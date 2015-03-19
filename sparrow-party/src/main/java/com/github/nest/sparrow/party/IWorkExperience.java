/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;
import java.util.Date;

import com.github.nest.sparrow.party.codes.IJobTitle;
import com.github.nest.sparrow.party.codes.IOccupation;

/**
 * work experience interface
 * 
 * @author brad.wu
 */
public interface IWorkExperience extends Serializable {
	/**
	 * get id
	 * 
	 * @return
	 */
	Long getExperienceId();

	/**
	 * set id
	 * 
	 * @param experienceId
	 */
	void setExperienceId(Long experienceId);

	/**
	 * get employer
	 * 
	 * @return
	 */
	IOrganization getEmployer();

	/**
	 * set employer
	 * 
	 * @param employer
	 */
	void setEmployer(IOrganization employer);

	/**
	 * get start from
	 * 
	 * @return
	 */
	Date getStartDate();

	/**
	 * set start date
	 * 
	 * @param startDate
	 */
	void setStartDate(Date startDate);

	/**
	 * get end to
	 * 
	 * @return
	 */
	Date getEndDate();

	/**
	 * set end date
	 * 
	 * @param endDate
	 */
	void setEndDate(Date endDate);

	/**
	 * get occupation
	 * 
	 * @return
	 */
	IOccupation getOccupation();

	/**
	 * set occupation
	 * 
	 * @param occupation
	 */
	void setOccupation(IOccupation occupation);

	/**
	 * get other occupation
	 * 
	 * @return
	 */
	String getOtherOccupation();

	/**
	 * set other occupation
	 * 
	 * @param otherOccupation
	 */
	void setOtherOccupation(String otherOccupation);

	/**
	 * get job title
	 * 
	 * @return
	 */
	IJobTitle getJobTitle();

	/**
	 * set job title
	 * 
	 * @param jobTitle
	 */
	void setJobTitle(IJobTitle jobTitle);

	/**
	 * get other job title
	 * 
	 * @return
	 */
	String getOtherJobTitle();

	/**
	 * set other job title
	 * 
	 * @param otherJobTitle
	 */
	void setOtherJobTitle(String otherJobTitle);
}
