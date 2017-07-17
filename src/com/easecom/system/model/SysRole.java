package com.easecom.system.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRole extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String remark;
	private String roleurl;
	private String shopId;
	private Set sysRoleFunrights = new HashSet(0);
	private Set sysUserRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(String name, String remark, String roleurl, String shopId, Set sysRoleFunrights,
			Set sysUserRoles) {
		this.name = name;
		this.remark = remark;
		this.roleurl = roleurl;
		this.shopId = shopId;
		this.sysRoleFunrights = sysRoleFunrights;
		this.sysUserRoles = sysUserRoles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

 
 
	public Set getSysRoleFunrights() {
		return sysRoleFunrights;
	}

	public void setSysRoleFunrights(Set sysRoleFunrights) {
		this.sysRoleFunrights = sysRoleFunrights;
	}

	public Set getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	public String getRoleurl() {
		return roleurl;
	}

	public void setRoleurl(String roleurl) {
		this.roleurl = roleurl;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}