/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;

import com.github.nest.sparrow.party.IEducationExperience;
import com.github.nest.sparrow.party.codes.IAcademicMajor;
import com.github.nest.sparrow.party.codes.IEducationDegree;
import com.github.nest.sparrow.party.generalization.IEducationOrganization;

/**
 * education experience
 * 
 * @author brad.wu
 */
public class EducationExperience implements IEducationExperience {
	private static final long serialVersionUID = 379326214658747655L;

	private Long experienceId = null;
	private IEducationOrganization organization = null;
	private IAcademicMajor academicMajor = null;
	private IEducationDegree degree = null;
	private Date startDate = null;
	private Date endDate = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#getExperienceId()
	 */
	@Override
	public Long getExperienceId() {
		return this.experienceId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#setExperienceId(java.lang.Long)
	 */
	@Override
	public void setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#getOrganization()
	 */
	@Override
	public IEducationOrganization getOrganization() {
		return this.organization;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#setOrganization(com.github.nest.sparrow.party.generalization.IEducationOrganization)
	 */
	@Override
	public void setOrganization(IEducationOrganization organization) {
		this.organization = organization;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#getAcademicMajor()
	 */
	@Override
	public IAcademicMajor getAcademicMajor() {
		return this.academicMajor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#setAcademicMajor(com.github.nest.sparrow.party.codes.IAcademicMajor)
	 */
	@Override
	public void setAcademicMajor(IAcademicMajor academicMajor) {
		this.academicMajor = academicMajor;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#getDegree()
	 */
	@Override
	public IEducationDegree getDegree() {
		return this.degree;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#setDegree(com.github.nest.sparrow.party.codes.IEducationDegree)
	 */
	@Override
	public void setDegree(IEducationDegree degree) {
		this.degree = degree;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#getStartDate()
	 */
	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#setStartDate(java.util.Date)
	 */
	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#getEndDate()
	 */
	@Override
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IEducationExperience#setEndDate(java.util.Date)
	 */
	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
