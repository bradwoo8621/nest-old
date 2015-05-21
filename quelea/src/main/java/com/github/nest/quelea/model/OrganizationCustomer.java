/**
 * 
 */
package com.github.nest.quelea.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.nest.quelea.codes.RoleType;

/**
 * organization customer
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_CUSTOMER")
@DiscriminatorValue(RoleType.CUSTOMER_ORGANIZATION)
public class OrganizationCustomer extends PartyRole implements ICustomer {
	private static final long serialVersionUID = -404692462320874153L;

	@Column(name = "IS_VIP")
	private Boolean vip = null;

	@Column(name = "AUTHORIZED_STAFF_ROLE_ID")
	private Long authorizedStaffRoleId = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.CUSTOMER_ORGANIZATION;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.ICustomer#isVip()
	 */
	@Override
	public Boolean isVip() {
		return this.vip;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.ICustomer#setVip(java.lang.Boolean)
	 */
	@Override
	public void setVip(Boolean vip) {
		this.vip = vip;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.ICustomer#getAuthorizedStaffRoleId()
	 */
	@Override
	public Long getAuthorizedStaffRoleId() {
		return this.authorizedStaffRoleId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.ICustomer#setAuthorizedStaffRoleId(java.lang.Long)
	 */
	@Override
	public void setAuthorizedStaffRoleId(Long authorizedStaffRoleId) {
		this.authorizedStaffRoleId = authorizedStaffRoleId;
	}
}
