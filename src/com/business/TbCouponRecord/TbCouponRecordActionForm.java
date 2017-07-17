package com.business.TbCouponRecord;

import com.easecom.common.framework.struts.BaseForm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by liubaibing on 2016/11/7.
 */
public class TbCouponRecordActionForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    private String id;
    private String couponCodeId;
    private String userId;
    private Timestamp useTime;
    private String orderId;
    private BigDecimal discountMoney;
    private String couponCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponCodeId() {
        return couponCodeId;
    }

    public void setCouponCodeId(String couponCodeId) {
        this.couponCodeId = couponCodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
