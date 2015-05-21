/**
 * 
 */
package com.github.nest.quelea.model;

/**
 * customer interface
 * 
 * @author brad.wu
 */
public interface ICustomer {
	/**
	 * is vip
	 * 
	 * @return
	 */
	Boolean isVip();

	/**
	 * set vip
	 * 
	 * @param vip
	 */
	void setVip(Boolean vip);

	/**
	 * get authorized staff(employee) role id
	 * 
	 * @return
	 */
	Long getAuthorizedStaffRoleId();

	/**
	 * set authorized staff(employee) role id
	 * 
	 * @param authorizedStaffRoleId
	 */
	void setAuthorizedStaffRoleId(Long authorizedStaffRoleId);
}
