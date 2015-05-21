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
 * Branch, branch or sub branch of system owner.
 * 
 * @author brad.wu
 */
@Entity
@Table(name = "T_BRANCH")
@DiscriminatorValue(RoleType.BRANCH)
public class Branch extends PartyRole {
	private static final long serialVersionUID = -1610033135410511450L;

	@Column(name = "IS_HEAD_OFFICE")
	private Boolean headOffice = null;

	@Column(name = "PROVINCE_CODE")
	private String provinceCode = null;

	@Column(name = "CITY_CODE")
	private String cityCode = null;

	@Column(name = "PARENT_BRANCH_ROLE_ID")
	private Long parentBranchRoleId = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.quelea.model.PartyRole#getRoleTypeCode()
	 */
	@Override
	public String getRoleTypeCode() {
		return RoleType.BRANCH;
	}

	/**
	 * @return the headOffice
	 */
	public Boolean getHeadOffice() {
		return headOffice;
	}

	/**
	 * @param headOffice
	 *            the headOffice to set
	 */
	public void setHeadOffice(Boolean headOffice) {
		this.headOffice = headOffice;
	}

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the parentBranchRoleId
	 */
	public Long getParentBranchRoleId() {
		return parentBranchRoleId;
	}

	/**
	 * @param parentBranchRoleId
	 *            the parentBranchRoleId to set
	 */
	public void setParentBranchRoleId(Long parentBranchRoleId) {
		this.parentBranchRoleId = parentBranchRoleId;
	}
}
