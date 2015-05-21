/**
 * 
 */
package com.github.nest.quelea.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.RoleType;

/**
 * individual agent
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_INDIVIDUAL_AGENT")
@DiscriminatorValue(RoleType.AGENT_INDIVIDUAL)
public class IndividualAgent extends PartyRole implements IAgent {
	private static final long serialVersionUID = -8741517553472003199L;

	@Column(name = "LICENSE_NUMBER")
	private String licenseNumber = null;

	@Column(name = "LICENSE_EXPIRY_DATE")
	@Temporal(TemporalType.DATE)
	private Date licenseExpiryDate = null;

	@Column(name = "STATUS_CODE")
	private String statusCode = null;

	@Column(name = "TERMINATED_AT")
	@Temporal(TemporalType.DATE)
	private Date terminatedAt = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.AGENT_INDIVIDUAL;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#setLicenseNumber(java.lang.String)
	 */
	@Override
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#getLicenseNumber()
	 */
	@Override
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#setLicenseExpiryDate(org.joda.time.DateTime)
	 */
	@Override
	public void setLicenseExpiryDate(DateTime licenseExpiryDate) {
		this.licenseExpiryDate = null == licenseExpiryDate ? null : licenseExpiryDate.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#getLicenseExpiryDate()
	 */
	@Override
	public DateTime getLicenseExpiryDate() {
		return this.licenseExpiryDate == null ? null : new DateTime(this.licenseExpiryDate);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#setStatusCode(java.lang.String)
	 */
	@Override
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#getStatusCode()
	 */
	@Override
	public String getStatusCode() {
		return this.statusCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#setTerminatedAt(org.joda.time.DateTime)
	 */
	@Override
	public void setTerminatedAt(DateTime terminatedAt) {
		this.terminatedAt = null == terminatedAt ? null : terminatedAt.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IAgent#getTerminatedAt()
	 */
	@Override
	public DateTime getTerminatedAt() {
		return this.terminatedAt == null ? null : new DateTime(this.terminatedAt);
	}
}
