package com.business.NetworkCourseOrderRefund;

import com.easecom.common.framework.struts.BaseForm;

import java.io.Serializable;

/**
 * Created by john on 2017/4/10.
 */
public class NetworkCourseOrderRefundActionForm extends BaseForm implements Serializable {
    private String id;
    private String productOrderDetailsId;
    private String createTime;
    private String orderCode;
    private String refundReasonId;
    private String remark;
    private Double fee;
    private Integer type;
    private String checkStatus;
    private String returnSid;
    private String eCode;
    private String eName;
    private Integer productStatus;
    private String img;
    private String phone;
    private Integer productType;
    private Integer timelyRefund;
    private Double bookPrice;
    private Integer playCount;
    private String applyCreatTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductOrderDetailsId() {
        return productOrderDetailsId;
    }

    public void setProductOrderDetailsId(String productOrderDetailsId) {
        this.productOrderDetailsId = productOrderDetailsId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRefundReasonId() {
        return refundReasonId;
    }

    public void setRefundReasonId(String refundReasonId) {
        this.refundReasonId = refundReasonId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getReturnSid() {
        return returnSid;
    }

    public void setReturnSid(String returnSid) {
        this.returnSid = returnSid;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getTimelyRefund() {
        return timelyRefund;
    }

    public void setTimelyRefund(Integer timelyRefund) {
        this.timelyRefund = timelyRefund;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public String getApplyCreatTime() {
        return applyCreatTime;
    }

    public void setApplyCreatTime(String applyCreatTime) {
        this.applyCreatTime = applyCreatTime;
    }

    @Override
    public String toString() {
        return "NetworkCourseOrderRefundActionForm{" +
                "id='" + id + '\'' +
                ", productOrderDetailsId='" + productOrderDetailsId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", refundReasonId='" + refundReasonId + '\'' +
                ", remark='" + remark + '\'' +
                ", fee=" + fee +
                ", type=" + type +
                ", checkStatus='" + checkStatus + '\'' +
                ", returnSid='" + returnSid + '\'' +
                ", eCode='" + eCode + '\'' +
                ", eName='" + eName + '\'' +
                ", productStatus=" + productStatus +
                ", img='" + img + '\'' +
                ", phone='" + phone + '\'' +
                ", productType=" + productType +
                ", timelyRefund=" + timelyRefund +
                ", bookPrice=" + bookPrice +
                ", playCount=" + playCount +
                ", applyCreatTime='" + applyCreatTime + '\'' +
                '}';
    }
}

