package com.business.coupon.dao;

import java.util.Date;

/**
 * CouponGainRecord entity. @author MyEclipse Persistence Tools
 */

public class CouponGainRecord extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String type;
	private String contentId;
	private String templateId;
	private String couponId;
	private Date gainTime;

	// Constructors

	/** default constructor */
	public CouponGainRecord() {
	}

	/** minimal constructor */
	public CouponGainRecord(String id) {
		this.id = id;
	}

	/** full constructor */
	public CouponGainRecord(String id, String userId, String type,
			String contentId, String templateId, String couponId,
			Date gainTime) {
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.contentId = contentId;
		this.templateId = templateId;
		this.couponId = couponId;
		this.gainTime = gainTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getCouponId() {
		return this.couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Date getGainTime() {
		return this.gainTime;
	}

	public void setGainTime(Date gainTime) {
		this.gainTime = gainTime;
	}

}