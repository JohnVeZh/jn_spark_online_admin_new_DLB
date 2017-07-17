package com.business.coupon.dao;

import java.util.Date;

/**
 * CouponTemplate entity. @author MyEclipse Persistence Tools
 */

public class CouponTemplate extends
		com.easecom.common.framework.hibernate.BaseModel implements
		java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String type;
	private String status;
	private Date effectTime;
	private Date invalidTime;
	private Date createTime;
	private Integer effectPeriod;
	private String discountType;
	private Double minMoney;
	private Double discountMoney;
	private Double discountRate;
	private String productType;
	private String productId;

	// Constructors

	/** default constructor */
	public CouponTemplate() {
	}

	/** minimal constructor */
	public CouponTemplate(String id) {
		this.id = id;
	}

	/** full constructor */
	public CouponTemplate(String id, String title, String type, String status,
			Date effectTime, Date invalidTime, Date createTime,
			Integer effectPeriod, String discountType, Double minMoney,
			Double discountMoney, Double discountRate, String productType,
			String productId) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.status = status;
		this.effectTime = effectTime;
		this.invalidTime = invalidTime;
		this.createTime = createTime;
		this.effectPeriod = effectPeriod;
		this.discountType = discountType;
		this.minMoney = minMoney;
		this.discountMoney = discountMoney;
		this.discountRate = discountRate;
		this.productType = productType;
		this.productId = productId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getEffectPeriod() {
		return this.effectPeriod;
	}

	public void setEffectPeriod(Integer effectPeriod) {
		this.effectPeriod = effectPeriod;
	}

	public String getDiscountType() {
		return this.discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getMinMoney() {
		return this.minMoney;
	}

	public void setMinMoney(Double minMoney) {
		this.minMoney = minMoney;
	}

	public Double getDiscountMoney() {
		return this.discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Double getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

}