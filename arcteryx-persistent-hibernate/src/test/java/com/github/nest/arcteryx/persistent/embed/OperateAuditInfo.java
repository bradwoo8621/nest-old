/**
 * 
 */
package com.github.nest.arcteryx.persistent.embed;

import java.sql.Timestamp;

/**
 * @author brad.wu
 *
 */
public class OperateAuditInfo {
	private Long createUserId = null;
	private Timestamp createTime = null;

	/**
	 * @return the createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
