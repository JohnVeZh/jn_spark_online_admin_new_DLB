package com.business.v2.special;

import java.util.Date;

/**
 * TbSpecialCatalog entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialCatalog extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String PId;
	private String name;
	private String iconUrl;
	private Integer sortOrder;
	private String remarks;
	private String isDel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbSpecialCatalog() {
	}

	/** minimal constructor */
	public TbSpecialCatalog(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbSpecialCatalog(String id, String PId, String name, String iconUrl,
			Integer sortOrder, String remarks, String isDel,
			Date createTime) {
		this.id = id;
		this.PId = PId;
		this.name = name;
		this.iconUrl = iconUrl;
		this.sortOrder = sortOrder;
		this.remarks = remarks;
		this.isDel = isDel;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}