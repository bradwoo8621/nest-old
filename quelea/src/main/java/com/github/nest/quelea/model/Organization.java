/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.OrganizationIdType;
import com.github.nest.quelea.codes.PartyType;

/**
 * organization party
 * 
 * @author brad.wu
 */
@Entity
@DiscriminatorValue(PartyType.ORGANIZATION)
public class Organization extends Party implements Serializable {
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
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName
	 *            the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the dateOfDeregister
	 */
	public DateTime getDateOfDeregister() {
		return dateOfDeregister == null ? null : new DateTime(this.dateOfDeregister);
	}

	/**
	 * @param dateOfDeregister
	 *            the dateOfDeregister to set
	 */
	public void setDateOfDeregister(DateTime dateOfDeregister) {
		this.dateOfDeregister = null == dateOfDeregister ? null : dateOfDeregister.toDate();
	}

	/**
	 * @return the dateOfRegister
	 */
	public DateTime getDateOfRegister() {
		return dateOfRegister == null ? null : new DateTime(this.dateOfRegister);
	}

	/**
	 * @param dateOfRegister
	 *            the dateOfRegister to set
	 */
	public void setDateOfRegister(DateTime dateOfRegister) {
		this.dateOfRegister = null == dateOfRegister ? null : dateOfRegister.toDate();
	}

	/**
	 * @return the idTypeCode
	 * @see OrganizationIdType
	 */
	public String getIdTypeCode() {
		return idTypeCode;
	}

	/**
	 * @param idTypeCode
	 *            the idTypeCode to set
	 * @see OrganizationIdType
	 */
	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}

	/**
	 * @return the legalRepresentative
	 */
	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	/**
	 * @param legalRepresentative
	 *            the legalRepresentative to set
	 */
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	/**
	 * @return the legalTypeCode
	 * @See LegalType
	 */
	public String getLegalTypeCode() {
		return legalTypeCode;
	}

	/**
	 * @param legalTypeCode
	 *            the legalTypeCode to set
	 * @See LegalType
	 */
	public void setLegalTypeCode(String legalTypeCode) {
		this.legalTypeCode = legalTypeCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.Party#getPartyTypeCode()
	 */
	@Override
	public String getPartyTypeCode() {
		return PartyType.ORGANIZATION;
	}
}
