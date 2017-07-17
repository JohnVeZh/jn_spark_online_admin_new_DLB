package com.business.TbCouponProduct;

import javax.persistence.*;

/**
 * Created by liubaibing on 2016/11/2.
 */
@Entity
@Table(name = "tb_coupon_product", schema = "jn_spark_online", catalog = "")
public class TbCouponProduct extends com.easecom.common.framework.hibernate.BaseModel
        implements java.io.Serializable{
    private String id;
    private String type;
    private String contentId;
    private String couponId;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "content_id", nullable = false, length = 32)
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "coupon_id", nullable = false, length = 32)
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCouponProduct that = (TbCouponProduct) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (contentId != null ? !contentId.equals(that.contentId) : that.contentId != null) return false;
        if (couponId != null ? !couponId.equals(that.couponId) : that.couponId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (contentId != null ? contentId.hashCode() : 0);
        result = 31 * result + (couponId != null ? couponId.hashCode() : 0);
        return result;
    }
}
