package com.business.NetworkCourseOrderRefund;

import java.io.Serializable;

/**
 * Created by john on 2017/4/10.
 */
public class NetworkCourseOrderRefund implements Serializable {

    private String id;
    private String productOrderDetailsId; // 订单商品表
    private String createTime;             //创建时间
    private String orderCode;              //退款订单编码
    private String refundReasonId;
    private String remark;
    private Double fee;    //退款金额
    private Integer type;   //类型 0 退款  1退款退货  默认0
    private String checkStatus; //'check_ing' COMMENT 'check_ing:退款审核中, check_passed:审核通过, check_refused:拒绝退款, pay_finished:已完成退款',
    private String returnSid;  //退货运单号
    private String eCode;   //物流公司编号
    private String eName;  //物流公司名称
    private Integer productStatus; //商品状态 0未收到货 1已收到货  默认0
    private String img;  //上传图片路径
    private String phone;
    private Integer productType;   //商品类型：0图书，1网课 ,2新网课  3新网课+图书
    private Integer timelyRefund;  //是否48小时内退款：0否，1是  默认0
    private Double bookPrice;  //礼包（附赠图书）费用 默认0
    private Integer playCount;  //已观看节数 默认0
    private String applyCreatTime;  //申请退款时间

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
        return "NetworkCourseOrderRefund{" +
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
