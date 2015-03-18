/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;
import java.util.Date;

import com.github.nest.sparrow.party.codes.IAcademicMajor;
import com.github.nest.sparrow.party.codes.IEducationDegree;
import com.github.nest.sparrow.party.generalization.IEducationalOrganization;

/**
 * education experience
 * 
 * @author brad.wu
 */
public interface IEducationExperience extends Serializable {
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
	 * get organization
	 * 
	 * @return
	 */
	IEducationalOrganization getOrganization();

	/**
	 * set organization
	 * 
	 * @param organization
	 */
	void setOrganization(IEducationalOrganization organization);

	/**
	 * get academic major(主修专业)
	 * 
	 * @return
	 */
	IAcademicMajor getAcademicMajor();

	/**
	 * set academic major
	 * 
	 * @param academicMajor
	 */
	void setAcademicMajor(IAcademicMajor academicMajor);

	/**
	 * get degree
	 * 
	 * @return
	 */
	IEducationDegree getDegree();

	/**
	 * set degree
	 * 
	 * @param degree
	 */
	void setDegree(IEducationDegree degree);

	/**
	 * get start from
	 * 
	 * @return
	 */
	Date getStartDate();

	/**
	 * get start date
	 * 
	 * @param date
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
}
