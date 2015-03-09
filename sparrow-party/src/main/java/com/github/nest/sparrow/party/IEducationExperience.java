/**
 * 
 */
package com.github.nest.sparrow.party;

import java.io.Serializable;
import java.util.Date;

import com.github.nest.sparrow.party.generalization.IEducationalOrganization;

/**
 * education experience
 * 
 * @author brad.wu
 */
public interface IEducationExperience extends Serializable {
	/**
	 * get employer
	 * 
	 * @return
	 */
	IEducationalOrganization getOrganization();

	/**
	 * get academic major(主修专业)
	 * 
	 * @return
	 */
	IAcademicMajor getAcademicMajor();

	/**
	 * get degree
	 * 
	 * @return
	 */
	IEducationDegree getDegree();

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
}
