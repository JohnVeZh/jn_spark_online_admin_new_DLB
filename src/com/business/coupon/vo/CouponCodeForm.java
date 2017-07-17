package com.business.coupon.vo;


import java.util.Date;

import com.easecom.common.framework.struts.BaseForm;

/**
 * CouponCode entity. @author MyEclipse Persistence Tools
 */

public class CouponCodeForm extends BaseForm {
	private static final long serialVersionUID = 1L;

	// Fields

	private String id;
	private String code;
	private String status;
	private Date effectTime;
	private Date invalidTime;
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}