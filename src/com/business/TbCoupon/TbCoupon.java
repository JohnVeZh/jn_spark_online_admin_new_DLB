package com.business.TbCoupon;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liubaibing on 2016/11/2.
 */
@Entity
@Table(name = "tb_coupon", schema = "jn_spark_online", catalog = "")
public class TbCoupon extends com.easecom.common.framework.hibernate.BaseModel
        implements java.io.Serializable{
    private String id;
    private String title;
    private byte type;
    private byte status;
    private int maxUseNum;
    private String startTime;
    private String endTime;
    private Integer minMoney;
    private Integer discountMoney;
    private Double discountRate;
    private String createTime;
    private byte relationType;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 64)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "max_use_num", nullable = false)
    public int getMaxUseNum() {
        return maxUseNum;
    }

    public void setMaxUseNum(int maxUseNum) {
        this.maxUseNum = maxUseNum;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "min_money", nullable = true)
    public Integer getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Integer minMoney) {
        this.minMoney = minMoney;
    }

    @Basic
    @Column(name = "discount_money", nullable = true)
    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    @Basic
    @Column(name = "discount_rate", nullable = true, precision = 0)
    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "relation_type", nullable = false)
    public byte getRelationType() {
        return relationType;
    }

    public void setRelationType(byte relationType) {
        this.relationType = relationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCoupon tbCoupon = (TbCoupon) o;

        if (type != tbCoupon.type) return false;
        if (status != tbCoupon.status) return false;
        if (maxUseNum != tbCoupon.maxUseNum) return false;
        if (relationType != tbCoupon.relationType) return false;
        if (id != null ? !id.equals(tbCoupon.id) : tbCoupon.id != null) return false;
        if (title != null ? !title.equals(tbCoupon.title) : tbCoupon.title != null) return false;
        if (startTime != null ? !startTime.equals(tbCoupon.startTime) : tbCoupon.startTime != null) return false;
        if (endTime != null ? !endTime.equals(tbCoupon.endTime) : tbCoupon.endTime != null) return false;
        if (minMoney != null ? !minMoney.equals(tbCoupon.minMoney) : tbCoupon.minMoney != null) return false;
        if (discountMoney != null ? !discountMoney.equals(tbCoupon.discountMoney) : tbCoupon.discountMoney != null)
            return false;
        if (discountRate != null ? !discountRate.equals(tbCoupon.discountRate) : tbCoupon.discountRate != null)
            return false;
        if (createTime != null ? !createTime.equals(tbCoupon.createTime) : tbCoupon.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (int) type;
        result = 31 * result + (int) status;
        result = 31 * result + maxUseNum;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (minMoney != null ? minMoney.hashCode() : 0);
        result = 31 * result + (discountMoney != null ? discountMoney.hashCode() : 0);
        result = 31 * result + (discountRate != null ? discountRate.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (int) relationType;
        return result;
    }
}
