/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.IPartyRole;

/**
 * organization party and role interface.<br>
 * for implement the concrete party role object, since multiple extension is not
 * permitted in java.
 * 
 * @author brad.wu
 */
public abstract class OrganizationAndRole extends Organization implements IPartyRole {
	private static final long serialVersionUID = 5127074376660325058L;

	private Long roleId = null;
	private String roleCode = null;
	private boolean enabled = true;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleId()
	 */
	@Override
	public Long getRoleId() {
		return this.roleId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleId(java.lang.Long)
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleCode()
	 */
	@Override
	public String getRoleCode() {
		return this.roleCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setRoleCode(java.lang.String)
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#setEnabled(boolean)
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
