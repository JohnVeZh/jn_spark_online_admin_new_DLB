package com.business.TbCouponCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liubaibing on 2016/11/2.
 */
@Entity
@Table(name = "tb_coupon_code", schema = "jn_spark_online", catalog = "")
public class TbCouponCode extends com.easecom.common.framework.hibernate.BaseModel
        implements java.io.Serializable{
    private String id;
    private String code;
    private String couponId;
    private int useNum;
    private String createTime;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 32)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "coupon_id", nullable = false, length = 32)
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    @Basic
    @Column(name = "use_num", nullable = false)
    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCouponCode that = (TbCouponCode) o;

        if (useNum != that.useNum) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (couponId != null ? !couponId.equals(that.couponId) : that.couponId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (couponId != null ? couponId.hashCode() : 0);
        result = 31 * result + useNum;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
