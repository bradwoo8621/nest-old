/**
 * 
 */
package com.github.nest.quelea.model;

import java.io.Serializable;

import org.joda.time.DateTime;

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
}
