package com.business.TbCouponCode;

import com.easecom.common.framework.struts.BaseForm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by liubaibing on 2016/11/7.
 */
public class TbCouponCodeActionForm extends BaseForm implements java.io.Serializable{
        private String id;
        private String code;
        private String couponId;
        private int useNum;
        private String createTime;
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
    public String getCouponId() {
        return couponId;
    }
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
    public int getUseNum() {
        return useNum;
    }
    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
