package com.easecom.system.model;

/**
 * SystemProvinceCity entity. @author MyEclipse Persistence Tools
 */

public class SystemProvinceCity extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String parentid;
	private String type;
	private String areaid;

	// Constructors

	/** default constructor */
	public SystemProvinceCity() {
	}

	/** full constructor */
	public SystemProvinceCity(String name, String parentid, String type,
			String areaid) {
		this.name = name;
		this.parentid = parentid;
		this.type = type;
		this.areaid = areaid;
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

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

}