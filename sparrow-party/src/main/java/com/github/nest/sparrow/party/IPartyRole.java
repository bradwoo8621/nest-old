/**
 * 
 */
package com.github.nest.sparrow.party;

/**
 * party role interface. <br>
 * TODO no property <code>roleType</code> defined because the class of
 * implementation can identify the role type.
 * 
 * @author brad.wu
 */
public interface IPartyRole extends IParty {
	/**
	 * get role id
	 * 
	 * @return
	 */
	Long getRoleId();

	/**
	 * set role id
	 * 
	 * @param roleId
	 */
	void setRoleId(Long roleId);

	/**
	 * party role code. unique in role type.
	 * 
	 * @return
	 */
	String getRoleCode();

	/**
	 * set role code
	 * 
	 * @param roleCode
	 */
	void setRoleCode(String roleCode);

	/**
	 * is enabled or not?
	 * 
	 * @return
	 */
	boolean isEnabled();

	/**
	 * set enabled
	 * 
	 * @param enabled
	 */
	void setEnabled(boolean enabled);
}
