/**
 * 
 */
package com.github.nest.arteryx.persistent;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * auditable
 * 
 * @author brad.wu
 */
public interface IAuditable extends Serializable {
	/**
	 * set last modified date
	 * 
	 * @param lastModifiedDate
	 */
	void setLastModifiedDate(DateTime lastModifiedDate);

	/**
	 * get last modified date
	 * 
	 * @return
	 */
	DateTime getLastModifiedDate();

	/**
	 * set last modified by
	 * 
	 * @param lastModifiedBy
	 */
	void setLastModifiedBy(Long lastModifiedBy);

	/**
	 * get last modified by
	 * 
	 * @return
	 */
	Long getLastModifiedBy();

	/**
	 * set created date
	 * 
	 * @param createdDate
	 */
	void setCreatedDate(DateTime createdDate);

	/**
	 * get created date
	 * 
	 * @return
	 */
	DateTime getCreatedDate();

	/**
	 * set created by
	 * 
	 * @param createdBy
	 */
	void setCreatedBy(Long createdBy);

	/**
	 * get created by
	 * 
	 * @return
	 */
	Long getCreatedBy();
}
