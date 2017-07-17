package com.business.coupon.dao;

import java.util.Date;


/**
 * CouponTemplateRelation entity. @author MyEclipse Persistence Tools
 */

public class CouponTemplateRelation extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private String templateId;
	private String contentId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public CouponTemplateRelation() {
	}

	/** minimal constructor */
	public CouponTemplateRelation(String id) {
		this.id = id;
	}

	/** full constructor */
	public CouponTemplateRelation(String id, String type, String templateId,
			String contentId, Date createTime) {
		this.id = id;
		this.type = type;
		this.templateId = templateId;
		this.contentId = contentId;
		this.createTime = createTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}