/**
 * 
 */
package com.github.nest.sparrow.party.internal;

import com.github.nest.sparrow.party.IPartyRole;
import com.github.nest.sparrow.party.IPartyRoleType;

/**
 * individual party and role interface.<br>
 * for implement the concrete party role object, since multiple extension is not
 * permitted in java.
 * 
 * @author brad.wu
 */
public abstract class IndividualAndRole extends Individual implements IPartyRole {
	private static final long serialVersionUID = 7099087522087708091L;

	private IPartyRoleType roleType = null;
	private String roleCode = null;
	private boolean enabled = true;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.party.IPartyRole#getRoleType()
	 */
	@Override
	public IPartyRoleType getRoleType() {
		return this.roleType;
	}

	/**
	 * @param roleType
	 *            the roleType to set
	 */
	public void setRoleType(IPartyRoleType roleType) {
		this.roleType = roleType;
	}

	/**
	 * @param roleCode
	 *            the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
	 * @see com.github.nest.sparrow.party.IPartyRole#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
