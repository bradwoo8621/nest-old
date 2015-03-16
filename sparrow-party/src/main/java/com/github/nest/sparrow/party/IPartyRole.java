/**
 * 
 */
package com.github.nest.sparrow.party;

import com.github.nest.goose.operate.OperateLog;

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
	 * is role enabled or not?
	 * 
	 * @return
	 */
	Boolean isRoleEnabled();

	/**
	 * set role enabled
	 * 
	 * @param enabled
	 */
	void setRoleEnabled(Boolean enabled);

	/**
	 * get operate log
	 * 
	 * @return
	 */
	OperateLog getRoleOperateLog();

	/**
	 * set operate log
	 * 
	 * @param operateLog
	 */
	void setRoleOperateLog(OperateLog operateLog);

	/**
	 * get optimistic lock
	 * 
	 * @return
	 */
	Long getRoleOptimisticLock();

	/**
	 * set optimistic lock
	 * 
	 * @param optimisticLock
	 */
	void setRoleOptimisticLock(Long optimisticLock);

	/**
	 * get party
	 * 
	 * @return
	 */
	IParty getParty();

	/**
	 * set party
	 * 
	 * @param party
	 */
	void setParty(IParty party);
}
