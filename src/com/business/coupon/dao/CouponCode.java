package com.business.coupon.dao;

import java.util.Date;


/**
 * CouponCode entity. @author MyEclipse Persistence Tools
 */
public class CouponCode extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String status;
	private Date effectTime;
	private Date invalidTime;
	private Date createTime;

	// Constructors

	/** default constructor */
	public CouponCode() {
	}

	/** minimal constructor */
	public CouponCode(String id) {
		this.id = id;
	}

	/** full constructor */
	public CouponCode(String id, String code, String status,
			Date effectTime, Date invalidTime, Date createTime) {
		this.id = id;
		this.code = code;
		this.status = status;
		this.effectTime = effectTime;
		this.invalidTime = invalidTime;
		this.createTime = createTime;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEffectTime() {
		return this.effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public Date getInvalidTime() {
		return this.invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}