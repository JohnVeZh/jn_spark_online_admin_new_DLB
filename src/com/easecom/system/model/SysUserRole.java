package com.easecom.system.model;

/**
 * SysUserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUserRole extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private SysRole sysRole;
	private SysUser sysUser;
	private String userName;
	private String roleName;
	private String remark;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** full constructor */
	public SysUserRole(SysRole sysRole, SysUser sysUser, String userName,
			String roleName, String remark) {
		this.sysRole = sysRole;
		this.sysUser = sysUser;
		this.userName = userName;
		this.roleName = roleName;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}