package com.business.TbCouponRecord;

import com.easecom.common.framework.hibernate.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by liubaibing on 2016/11/2.
 */
@Entity
@Table(name = "tb_coupon_record", schema = "jn_spark_online", catalog = "")
public class TbCouponRecord extends BaseModel
        implements Serializable{
    private String id;
    private String couponCodeId;
    private String userId;
    private Timestamp useTime;
    private String orderId;
    private BigDecimal discountMoney;
    private String couponCode;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coupon_code_id", nullable = false, length = 32)
    public String getCouponCodeId() {
        return couponCodeId;
    }

    public void setCouponCodeId(String couponCodeId) {
        this.couponCodeId = couponCodeId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "use_time", nullable = false)
    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    @Basic
    @Column(name = "order_id", nullable = false, length = 32)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "discount_money", nullable = false, precision = 2)
    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCouponRecord that = (TbCouponRecord) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (couponCodeId != null ? !couponCodeId.equals(that.couponCodeId) : that.couponCodeId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (useTime != null ? !useTime.equals(that.useTime) : that.useTime != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (discountMoney != null ? !discountMoney.equals(that.discountMoney) : that.discountMoney != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (couponCodeId != null ? couponCodeId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (useTime != null ? useTime.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (discountMoney != null ? discountMoney.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "coupon_code", nullable = false, length = 32)
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
