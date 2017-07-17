package com.easecom.system.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysOrg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysOrg extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable {

	// Fields

	private String id;
	private SysOrg sysOrg;
	private String code;
	private String name;
	private String fullname;
	private Long sort;
	private String remark;
	private String type;
	private String isvalid;
	private String logoPath;
	private Set sysOrgs = new HashSet(0);
	private Set sysUsers = new HashSet(0);
	private Set sysCompUsers = new HashSet(0);
	// Constructors
	/** default constructor */
	public SysOrg() {
	}

	/** full constructor */
	public SysOrg(SysOrg sysOrg, String code, String name, String fullname,
			Long sort, String remark, String type,String isvalid,String logoPath,Set surveyPapers, Set sysOrgs,
			Set sourceShares, Set newses, Set notices, Set fileUploads,
			Set sysUsers,Set sysCompUsers) {
		this.sysOrg = sysOrg;
		this.code = code;
		this.name = name;
		this.fullname = fullname;
		this.sort = sort;
		this.remark = remark;
		this.type = type;
		this.isvalid=isvalid;
		this.logoPath = logoPath;
		this.sysOrgs = sysOrgs;
		this.sysUsers = sysUsers;
		this.sysCompUsers = sysCompUsers;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysOrg getSysOrg() {
		return this.sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	 

	public Set getSysOrgs() {
		return this.sysOrgs;
	}

	public void setSysOrgs(Set sysOrgs) {
		this.sysOrgs = sysOrgs;
	}

	 

	 

	public Set getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set sysUsers) {
		this.sysUsers = sysUsers;
	}

	public Set getSysCompUsers() {
		return sysCompUsers;
	}

	public void setSysCompUsers(Set sysCompUsers) {
		this.sysCompUsers = sysCompUsers;
	}
	
	 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

}