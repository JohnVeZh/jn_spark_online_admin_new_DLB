package com.easecom.system.model;

/**
 * SysConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysConfig extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable {

	// Fields

	private String id;
	private String alias;
	private String name;
	private String value;
	private String jumpType;
	private String url;
	private String type;
	private Integer sort;

	// Constructors

	/** default constructor */
	public SysConfig() {
	}

	/** full constructor */
	public SysConfig(String alias, String name, String value, String type, String url,
			Integer sort) {
		this.alias = alias;
		this.name = name;
		this.value = value;
		this.type = type;
		this.url = url;
		this.sort = sort;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public String getJumpType() {
		return jumpType;
	}

	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}