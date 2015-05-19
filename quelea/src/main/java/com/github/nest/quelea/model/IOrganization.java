/**
 * 
 */
package com.github.nest.quelea.model;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.LegalType;
import com.github.nest.quelea.codes.OrganizationIdType;

/**
 * organization
 * 
 * @author brad.wu
 */
public interface IOrganization extends IParty {
	/**
	 * get organization name
	 * 
	 * @return
	 */
	String getOrganizationName();

	/**
	 * set organization name
	 * 
	 * @param organizationName
	 */
	void setOrganizationName(String organizationName);

	/**
	 * get date of registration
	 * 
	 * @return
	 */
	DateTime getDateOfRegister();

	/**
	 * set date of registration
	 * 
	 * @param dateOfRegister
	 */
	void setDateOfRegister(DateTime dateOfRegister);

	/**
	 * get date of deregistration
	 * 
	 * @return
	 */
	DateTime getDateOfDeregister();

	/**
	 * set date of deregistration
	 * 
	 * @param dateOfDeregister
	 */
	void setDateOfDeregister(DateTime dateOfDeregister);

	/**
	 * get id type code
	 * 
	 * @return
	 * @see OrganizationIdType
	 */
	String getIdTypeCode();

	/**
	 * set id type code
	 * 
	 * @param idTypeCode
	 * @see OrganizationIdType
	 */
	void setIdTypeCode(String idTypeCode);

	/**
	 * get legal type code
	 * 
	 * @return
	 * @see LegalType
	 */
	String getLegalTypeCode();

	/**
	 * set legal type code
	 * 
	 * @param legalTypeCode
	 * @see LegalType
	 */
	void setLegalTypeCode(String legalTypeCode);

	/**
	 * get legal representative
	 * 
	 * @return
	 */
	String getLegalRepresentative();

	/**
	 * set legal representative
	 * 
	 * @param legalRepresentative
	 */
	void setLegalRepresentative(String legalRepresentative);
}
