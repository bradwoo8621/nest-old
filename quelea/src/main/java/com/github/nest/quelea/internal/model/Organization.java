/**
 * 
 */
package com.github.nest.quelea.internal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.PartyType;
import com.github.nest.quelea.model.IOrganization;

/**
 * organization party
 * 
 * @author brad.wu
 */
@Entity
@DiscriminatorValue(PartyType.ORGANIZATION)
public class Organization extends Party implements IOrganization {
	private static final long serialVersionUID = -6771129290812660856L;

	@Column(name = "ORGANIZATION_NAME")
	private String organizationName = null;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfDeregister = null;

	@Column(name = "DATE_OF_DEATH")
	private Date dateOfRegister = null;

	@Column(name = "ID_TYPE_CODE")
	private String idTypeCode = null;

	@Column(name = "LEGAL_REPRESENTATIVE")
	private String legalRepresentative = null;

	@Column(name = "LEGAL_TYPE_CODE")
	private String legalTypeCode = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#getOrganizationName()
	 */
	@Override
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#setOrganizationName(java.lang.String)
	 */
	@Override
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#getDateOfRegister()
	 */
	@Override
	public DateTime getDateOfRegister() {
		return this.dateOfRegister == null ? null : new DateTime(this.dateOfRegister);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#setDateOfRegister(org.joda.time.DateTime)
	 */
	@Override
	public void setDateOfRegister(DateTime dateOfRegister) {
		this.dateOfRegister = null == dateOfRegister ? null : dateOfRegister.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#getDateOfDeregister()
	 */
	@Override
	public DateTime getDateOfDeregister() {
		return this.dateOfDeregister == null ? null : new DateTime(this.dateOfDeregister);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#setDateOfDeregister(org.joda.time.DateTime)
	 */
	@Override
	public void setDateOfDeregister(DateTime dateOfDeregister) {
		this.dateOfDeregister = null == dateOfDeregister ? null : dateOfDeregister.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#getIdTypeCode()
	 */
	@Override
	public String getIdTypeCode() {
		return this.idTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#setIdTypeCode(java.lang.String)
	 */
	@Override
	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#getLegalTypeCode()
	 */
	@Override
	public String getLegalTypeCode() {
		return this.legalTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#setLegalTypeCode(java.lang.String)
	 */
	@Override
	public void setLegalTypeCode(String legalTypeCode) {
		this.legalTypeCode = legalTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#getLegalRepresentative()
	 */
	@Override
	public String getLegalRepresentative() {
		return this.legalRepresentative;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.IOrganization#setLegalRepresentative(java.lang.String)
	 */
	@Override
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.internal.model.Party#getPartyTypeCode()
	 */
	@Override
	public String getPartyTypeCode() {
		return PartyType.ORGANIZATION;
	}
}
