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
 * organization agent
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_AGENT")
@DiscriminatorValue(RoleType.AGENT_ORGANIZATION)
public class OrganizationAgent extends PartyRole implements IAgent {
	private static final long serialVersionUID = -8741517553472003199L;

	@Column(name = "LICENSE_NUMBER")
	private String licenseNumber = null;

	@Column(name = "LICENSE_EXPIRY_DATE")
	@Temporal(TemporalType.DATE)
	private Date licenseExpiryDate = null;


	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.AGENT_ORGANIZATION;
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
}
