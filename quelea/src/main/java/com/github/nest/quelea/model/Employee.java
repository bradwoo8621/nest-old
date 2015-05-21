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
 * employee of system owner.
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_EMPLOYEE")
@DiscriminatorValue(RoleType.EMPLOYEE)
public class Employee extends PartyRole {
	private static final long serialVersionUID = -2521185485431010337L;

	@Column(name = "WORK_EMAIL")
	private String workEmail = null;

	@Column(name = "WORK_CELLPHONE")
	private String workCellphone = null;

	/**
	 * department of employee
	 */
	@Column(name = "DEPT_ROLE_ID")
	private Long departmentRoleId = null;

	/**
	 * branch of employee
	 */
	@Column(name = "BRANCH_ROLE_ID")
	private Long branchRoleId = null;

	/**
	 * supervisor of employee
	 */
	@Column(name = "SUPERVISTOR_EMP_ROLE_ID")
	private Long supervisorEmployeeRoleId = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.EMPLOYEE;
	}

	/**
	 * @return the workEmail
	 */
	public String getWorkEmail() {
		return workEmail;
	}

	/**
	 * @param workEmail
	 *            the workEmail to set
	 */
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	/**
	 * @return the workCellphone
	 */
	public String getWorkCellphone() {
		return workCellphone;
	}

	/**
	 * @param workCellphone
	 *            the workCellphone to set
	 */
	public void setWorkCellphone(String workCellphone) {
		this.workCellphone = workCellphone;
	}

	/**
	 * @return the departmentRoleId
	 */
	public Long getDepartmentRoleId() {
		return departmentRoleId;
	}

	/**
	 * @param departmentRoleId
	 *            the departmentRoleId to set
	 */
	public void setDepartmentRoleId(Long departmentRoleId) {
		this.departmentRoleId = departmentRoleId;
	}

	/**
	 * @return the branchRoleId
	 */
	public Long getBranchRoleId() {
		return branchRoleId;
	}

	/**
	 * @param branchRoleId
	 *            the branchRoleId to set
	 */
	public void setBranchRoleId(Long branchRoleId) {
		this.branchRoleId = branchRoleId;
	}

	/**
	 * @return the supervisorEmployeeRoleId
	 */
	public Long getSupervisorEmployeeRoleId() {
		return supervisorEmployeeRoleId;
	}

	/**
	 * @param supervisorEmployeeRoleId
	 *            the supervisorEmployeeRoleId to set
	 */
	public void setSupervisorEmployeeRoleId(Long supervisorEmployeeRoleId) {
		this.supervisorEmployeeRoleId = supervisorEmployeeRoleId;
	}
}
