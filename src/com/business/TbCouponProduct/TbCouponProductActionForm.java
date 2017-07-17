package com.business.TbCouponProduct;

import com.easecom.common.framework.struts.BaseForm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by liubaibing on 2016/11/7.
 */
public class TbCouponProductActionForm  extends BaseForm {
    private String id;
    private String type;
    private String contentId;
    private String couponId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

}
