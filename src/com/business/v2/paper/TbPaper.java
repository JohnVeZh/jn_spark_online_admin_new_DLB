package com.business.v2.paper;

import java.util.Date;

/**
 * TbPaper entity. @author MyEclipse Persistence Tools
 */

public class TbPaper extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable {

	// Fields

	private String id;
	private String section;
	private String catalogId;
	private String title;
	private String listImg;
	private String viewImg;
	private Integer sortOrder;
	private String isDel;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TbPaper() {
	}

	/** minimal constructor */
	public TbPaper(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbPaper(String id, String section, String catalogId, String title,
			String listImg, String viewImg, Integer sortOrder, String isDel,
			Date createTime) {
		this.id = id;
		this.section = section;
		this.catalogId = catalogId;
		this.title = title;
		this.listImg = listImg;
		this.viewImg = viewImg;
		this.sortOrder = sortOrder;
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

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListImg() {
		return this.listImg;
	}

	public void setListImg(String listImg) {
		this.listImg = listImg;
	}

	public String getViewImg() {
		return this.viewImg;
	}

	public void setViewImg(String viewImg) {
		this.viewImg = viewImg;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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