/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import java.util.Date;

import com.github.nest.sparrow.party.IOrganization;
import com.github.nest.sparrow.party.IPartyRole;
import com.github.nest.sparrow.party.IWorkExperience;
import com.github.nest.sparrow.party.codes.IJobTitle;
import com.github.nest.sparrow.party.codes.IOccupation;

/**
 * work experience
 * 
 * @author brad.wu
 */
public class WorkExperience implements IWorkExperience {
	private static final long serialVersionUID = -3825926898328257197L;

	private Long experienceId = null;
	private IOrganization employer = null;
	private Date endDate = null;
	private IJobTitle jobTitle = null;
	private IOccupation occupation = null;
	private String otherJobTitle = null;
	private String otherOccupation = null;
	private Date startDate = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getExperienceId()
	 */
	@Override
	public Long getExperienceId() {
		return this.experienceId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setExperienceId(java.lang.Long)
	 */
	@Override
	public void setExperienceId(Long experienceId) {
		this.experienceId = experienceId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getEmployer()
	 */
	@Override
	public IOrganization getEmployer() {
		return this.employer;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setEmployer(com.github.nest.sparrow.party.IOrganization)
	 */
	@Override
	public void setEmployer(IOrganization employer) {
		if (employer != null && employer instanceof IPartyRole) {
			this.employer = (IOrganization) ((IPartyRole) employer).getParty();
		} else {
			this.employer = employer;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getStartDate()
	 */
	@Override
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setStartDate(java.util.Date)
	 */
	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getEndDate()
	 */
	@Override
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setEndDate(java.util.Date)
	 */
	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getOccupation()
	 */
	@Override
	public IOccupation getOccupation() {
		return this.occupation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setOccupation(com.github.nest.sparrow.party.codes.IOccupation)
	 */
	@Override
	public void setOccupation(IOccupation occupation) {
		this.occupation = occupation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getOtherOccupation()
	 */
	@Override
	public String getOtherOccupation() {
		return this.otherOccupation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setOtherOccupation(java.lang.String)
	 */
	@Override
	public void setOtherOccupation(String otherOccupation) {
		this.otherOccupation = otherOccupation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getJobTitle()
	 */
	@Override
	public IJobTitle getJobTitle() {
		return this.jobTitle;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setJobTitle(com.github.nest.sparrow.party.codes.IJobTitle)
	 */
	@Override
	public void setJobTitle(IJobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#getOtherJobTitle()
	 */
	@Override
	public String getOtherJobTitle() {
		return this.otherJobTitle;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IWorkExperience#setOtherJobTitle(java.lang.String)
	 */
	@Override
	public void setOtherJobTitle(String otherJobTitle) {
		this.otherJobTitle = otherJobTitle;
	}
}
