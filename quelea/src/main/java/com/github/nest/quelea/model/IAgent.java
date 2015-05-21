/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;

import org.joda.time.DateTime;

import com.github.nest.quelea.codes.AgentStatus;

/**
 * agent interface
 * 
 * @author brad.wu
 */
public interface IAgent extends Serializable {
	/**
	 * set license number
	 * 
	 * @param licenseNumber
	 */
	void setLicenseNumber(String licenseNumber);

	/**
	 * get license number
	 * 
	 * @return
	 */
	String getLicenseNumber();

	/**
	 * set license expiry date
	 * 
	 * @param licenseExpiryDate
	 */
	void setLicenseExpiryDate(DateTime licenseExpiryDate);

	/**
	 * get license expiry date
	 * 
	 * @return
	 */
	DateTime getLicenseExpiryDate();

	/**
	 * set status code
	 * 
	 * @param statusCode
	 * @see AgentStatus
	 */
	void setStatusCode(String statusCode);

	/**
	 * get status code
	 * 
	 * @return
	 * @see AgentStatus
	 */
	String getStatusCode();

	/**
	 * set terminated at
	 * 
	 * @param terminatedAt
	 */
	void setTerminatedAt(DateTime terminatedAt);

	/**
	 * get terminated at
	 * 
	 * @return
	 */
	DateTime getTerminatedAt();
}
