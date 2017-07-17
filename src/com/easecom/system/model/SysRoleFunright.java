package com.easecom.system.model;

/**
 * SysRoleFunright entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRoleFunright extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private SysRole sysRole;
	private SysFunction sysFunction;
	private String funName;
	private String roleName;
	private String remark;

	// Constructors

	/** default constructor */
	public SysRoleFunright() {
	}

	/** full constructor */
	public SysRoleFunright(SysRole sysRole, SysFunction sysFunction,
			String funName, String roleName, String remark) {
		this.sysRole = sysRole;
		this.sysFunction = sysFunction;
		this.funName = funName;
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

	public SysFunction getSysFunction() {
		return this.sysFunction;
	}

	public void setSysFunction(SysFunction sysFunction) {
		this.sysFunction = sysFunction;
	}

	public String getFunName() {
		return this.funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
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