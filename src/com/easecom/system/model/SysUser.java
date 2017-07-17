package com.easecom.system.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUser extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private SysOrg sysOrg;
	private String orgName;
	private String name;
	private String loginName;
	private String password;
	private String workPhone;
	private String email;
	private String sex;
	private String age;
	private String duty;
	private String remark;
	private String isvalid;
	private String type;
	private String shopId;
	private Set sysUserRoles = new HashSet(0);
	
	// Constructors

	public Set getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(String code, SysOrg sysOrg, String orgName, String name,
			String loginName, String password, String workPhone, String email,
			String sex, String age, String duty, String remark, String isvalid,
			String type,String shopId ) {
		this.code = code;
		this.sysOrg=sysOrg;
		this.orgName = orgName;
		this.name = name;
		this.loginName = loginName;
		this.password = password;
		this.workPhone = workPhone;
		this.email = email;
		this.sex = sex;
		this.age = age;
		this.duty = duty;
		this.remark = remark;
		this.isvalid = isvalid;
		this.type = type;
		this.shopId = shopId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public SysOrg getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	 
}