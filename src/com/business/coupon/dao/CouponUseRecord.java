package com.business.coupon.dao;

import java.util.Date;

/**
 * CouponUseRecord entity. @author MyEclipse Persistence Tools
 */

public class CouponUseRecord extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String couponId;
	private String userId;
	private String orderId;
	private Double discountMoney;
	private Date useTime;

	// Constructors

	/** default constructor */
	public CouponUseRecord() {
	}

	/** minimal constructor */
	public CouponUseRecord(String id) {
		this.id = id;
	}

	/** full constructor */
	public CouponUseRecord(String id, String couponId, String userId,
			String orderId, Double discountMoney, Date useTime) {
		this.id = id;
		this.couponId = couponId;
		this.userId = userId;
		this.orderId = orderId;
		this.discountMoney = discountMoney;
		this.useTime = useTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCouponId() {
		return this.couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getDiscountMoney() {
		return this.discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Date getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

}