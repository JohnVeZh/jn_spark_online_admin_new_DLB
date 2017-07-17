package com.easecom.system.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SysFunction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysFunction extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private SysFunction sysFunction;
	private String code;
	private String name;
	private String fullname;
	private String url;
	private Long sort;
	private String isdir;
	private String isview;
	private String isvalid;
	private String remark;
	private String icon;
//	private int state;
	private Set sysRoleFunrights = new HashSet(0);
	private Set sysFunctions = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysFunction() {
	}

	/** full constructor */
	public SysFunction(SysFunction sysFunction, String code, String name,
			String fullname, String url, Long sort, String isdir,
			String isview, String isvalid, String remark,String icon, Set sysRoleFunrights,
			Set sysFunctions) {
		this.sysFunction = sysFunction;
		this.code = code;
		this.name = name;
		this.fullname = fullname;
		this.url = url;
		this.sort = sort;
		this.isdir = isdir;
		this.isview = isview;
		this.isvalid = isvalid;
		this.remark = remark;
		this.icon = icon;
		this.sysRoleFunrights = sysRoleFunrights;
		this.sysFunctions = sysFunctions;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysFunction getSysFunction() {
		return this.sysFunction;
	}

	public void setSysFunction(SysFunction sysFunction) {
		this.sysFunction = sysFunction;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getIsdir() {
		return this.isdir;
	}

	public void setIsdir(String isdir) {
		this.isdir = isdir;
	}

	public String getIsview() {
		return this.isview;
	}

	public void setIsview(String isview) {
		this.isview = isview;
	}

	public String getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getSysRoleFunrights() {
		return this.sysRoleFunrights;
	}

	public void setSysRoleFunrights(Set sysRoleFunrights) {
		this.sysRoleFunrights = sysRoleFunrights;
	}

	public Set getSysFunctions() {
		return this.sysFunctions;
	}

	public void setSysFunctions(Set sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

//	public int getState() {
//		return state;
//	}
//
//	public void setState(int state) {
//		this.state = state;
//	}

}