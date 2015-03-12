/**
 * 
 */
package com.github.nest.goose.operate;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * operate log
 * 
 * @author brad.wu
 */
public class OperateLog implements Serializable {
	private static final long serialVersionUID = -6080138991979414224L;
	private Long createUserId = null;
	private Timestamp createTime = null;
	private Long lastModifyUserId = null;
	private Timestamp lastModifyTime = null;

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

	/**
	 * @return the lastModifyUserId
	 */
	public Long getLastModifyUserId() {
		return lastModifyUserId;
	}

	/**
	 * @param lastModifyUserId
	 *            the lastModifyUserId to set
	 */
	public void setLastModifyUserId(Long lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	/**
	 * @return the lastModifyTime
	 */
	public Timestamp getLastModifyTime() {
		return lastModifyTime;
	}

	/**
	 * @param lastModifyTime
	 *            the lastModifyTime to set
	 */
	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
}
