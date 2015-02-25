/**
 * 
 */
package com.github.nest.arcteryx.persistent.embed;

/**
 * @author brad.wu
 *
 */
public class Person {
	private Long id = null;
	private String name = null;
	private OperateAuditInfo operateAuditInfo = null;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the operateAuditInfo
	 */
	public OperateAuditInfo getOperateAuditInfo() {
		return operateAuditInfo;
	}

	/**
	 * @param operateAuditInfo
	 *            the operateAuditInfo to set
	 */
	public void setOperateAuditInfo(OperateAuditInfo operateAuditInfo) {
		this.operateAuditInfo = operateAuditInfo;
	}
}