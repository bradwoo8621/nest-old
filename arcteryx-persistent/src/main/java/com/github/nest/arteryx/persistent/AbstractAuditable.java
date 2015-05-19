/**
 * 
 */
package com.github.nest.arteryx.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Auditable
 * 
 * @author brad.wu
 */
@MappedSuperclass
public abstract class AbstractAuditable implements IAuditable {
	private static final long serialVersionUID = 1606419657970449240L;

	@CreatedBy
	@Column(name = "CREATED_BY")
	private Long createdBy;

	@CreatedDate
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@LastModifiedBy
	@Column(name = "LAST_MODIFIED_BY")
	private Long lastModifiedBy;

	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#getCreatedBy()
	 */
	@Override
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#setCreatedBy(java.lang.Object)
	 */
	@Override
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#getCreatedDate()
	 */
	@Override
	public DateTime getCreatedDate() {
		return null == createdDate ? null : new DateTime(createdDate);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#setCreatedDate(org.joda.time.DateTime)
	 */
	@Override
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#getLastModifiedBy()
	 */
	@Override
	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#setLastModifiedBy(java.lang.Object)
	 */
	@Override
	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#getLastModifiedDate()
	 */
	@Override
	public DateTime getLastModifiedDate() {
		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arteryx.persistent.IAuditable#setLastModifiedDate(org.joda.time.DateTime)
	 */
	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
	}
}
