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

	private Address address = null;
	private Country bornIn = null;

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

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the bornIn
	 */
	public Country getBornIn() {
		return bornIn;
	}

	/**
	 * @param bornIn
	 *            the bornIn to set
	 */
	public void setBornIn(Country bornIn) {
		this.bornIn = bornIn;
	}
}