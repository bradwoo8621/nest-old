/**
 * 
 */
package com.github.nest.quelea.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.nest.quelea.codes.DepartmentType;
import com.github.nest.quelea.codes.RoleType;

/**
 * department of system owner.
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_DEPARTMENT")
@DiscriminatorValue(RoleType.DEPARTMENT)
public class Department extends PartyRole {
	private static final long serialVersionUID = -9026570385221667265L;

	@Column(name = "DEPT_TYPE_CODE")
	private String departmentTypeCode = null;

	@Column(name = "BRANCH_ROLE_ID")
	private Long branchRoleId = null;

	@Column(name = "PARENT_DEPT_ROLE_ID")
	private Long parentDepartmentRoleId = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.DEPARTMENT;
	}

	/**
	 * @return the departmentTypeCode
	 * @see DepartmentType
	 */
	public String getDepartmentTypeCode() {
		return departmentTypeCode;
	}

	/**
	 * @param departmentTypeCode
	 *            the departmentTypeCode to set
	 * @see DepartmentType
	 */
	public void setDepartmentTypeCode(String departmentTypeCode) {
		this.departmentTypeCode = departmentTypeCode;
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
	 * @return the parentDepartmentRoleId
	 */
	public Long getParentDepartmentRoleId() {
		return parentDepartmentRoleId;
	}

	/**
	 * @param parentDepartmentRoleId
	 *            the parentDepartmentRoleId to set
	 */
	public void setParentDepartmentRoleId(Long parentDepartmentRoleId) {
		this.parentDepartmentRoleId = parentDepartmentRoleId;
	}
}
