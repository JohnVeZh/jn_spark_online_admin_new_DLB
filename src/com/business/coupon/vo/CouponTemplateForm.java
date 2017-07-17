package com.business.coupon.vo;

import java.util.Date;

import com.easecom.common.framework.struts.BaseForm;

/**
 * CouponTemplate entity. @author MyEclipse Persistence Tools
 */

public class CouponTemplateForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	
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
	
	// ext fields
	private String productName;
	private String effectTimeStr;
	private String invalidTimeStr;
	private long gainNum;
	private long useNum;
	
	// Constructors

	public String getEffectTimeStr() {
		return effectTimeStr;
	}

	public void setEffectTimeStr(String effectTimeStr) {
		this.effectTimeStr = effectTimeStr;
	}

	public String getInvalidTimeStr() {
		return invalidTimeStr;
	}

	public void setInvalidTimeStr(String invalidTimeStr) {
		this.invalidTimeStr = invalidTimeStr;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/** default constructor */
	public CouponTemplateForm() {
	}

	/** minimal constructor */
	public CouponTemplateForm(String id) {
		this.id = id;
	}

	/** full constructor */
	public CouponTemplateForm(String id, String title, String type,
			Integer effectPeriod, String discountType, Double minMoney,
			Double discountMoney, Double discountRate, String productType,
			String productId, Date createTime) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.effectPeriod = effectPeriod;
		this.discountType = discountType;
		this.minMoney = minMoney;
		this.discountMoney = discountMoney;
		this.discountRate = discountRate;
		this.productType = productType;
		this.productId = productId;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getEffectPeriod() {
		return effectPeriod;
	}

	public void setEffectPeriod(Integer effectPeriod) {
		this.effectPeriod = effectPeriod;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(Double minMoney) {
		this.minMoney = minMoney;
	}

	public Double getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public long getGainNum() {
		return gainNum;
	}

	public void setGainNum(long gainNum) {
		this.gainNum = gainNum;
	}

	public long getUseNum() {
		return useNum;
	}

	public void setUseNum(long useNum) {
		this.useNum = useNum;
	}
}