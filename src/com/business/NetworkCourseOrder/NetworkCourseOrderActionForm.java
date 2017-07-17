package com.business.NetworkCourseOrder;

import com.easecom.common.framework.struts.BaseForm;
import org.apache.struts.upload.FormFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by john on 2017/3/27.
 */
public class NetworkCourseOrderActionForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private Double price;//商品售价
    private String userId;//用户id
    private String provinceId;//省份
    private String cityId;//市
    private String areaId;//区县
    private String zipCode;//邮编
    private String consignee;//收货人
    private String telephone;//收货人电话
    private String address;//收货地址
    private String createTime;//创建时间
    private String orderState;//订单状态
    private String logisticsCode;//物流单号
    private String remark;//备注
    private Integer userDel;//用户是否删除，0否，1是
    private Integer adminDel;//管理员删除，0否，1是
    private String orderCode;//订单号
    private Double postage;//邮费
    private Integer isPostage;//是否包邮，0否，1是
    private Integer type;//订单类型 0纯图书，1纯网课，2图书+网课 3新纯网课 4图书+新网课
    private String districtCn;//省市区县全称
    private Integer payType;//0 支付宝，1 微信
    private BigDecimal payPrice;//实付金额
    private Integer formType;//来源 1.app 2.wap 3.pc
    private String payTime;//支付时间
    private String autoRewardTime;//自动收货时间
    private FormFile file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserDel() {
        return userDel;
    }

    public void setUserDel(Integer userDel) {
        this.userDel = userDel;
    }

    public Integer getAdminDel() {
        return adminDel;
    }

    public void setAdminDel(Integer adminDel) {
        this.adminDel = adminDel;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Double getPostage() {
        return postage;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public Integer getIsPostage() {
        return isPostage;
    }

    public void setIsPostage(Integer isPostage) {
        this.isPostage = isPostage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDistrictCn() {
        return districtCn;
    }

    public void setDistrictCn(String districtCn) {
        this.districtCn = districtCn;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getAutoRewardTime() {
        return autoRewardTime;
    }

    public void setAutoRewardTime(String autoRewardTime) {
        this.autoRewardTime = autoRewardTime;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "NetworkCourseOrderActionForm{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", userId='" + userId + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", consignee='" + consignee + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderState='" + orderState + '\'' +
                ", logisticsCode='" + logisticsCode + '\'' +
                ", remark='" + remark + '\'' +
                ", userDel=" + userDel +
                ", adminDel=" + adminDel +
                ", orderCode='" + orderCode + '\'' +
                ", postage=" + postage +
                ", isPostage=" + isPostage +
                ", type=" + type +
                ", districtCn='" + districtCn + '\'' +
                ", payType=" + payType +
                ", payPrice=" + payPrice +
                ", formType=" + formType +
                ", payTime=" + payTime +
                ", autoRewardTime=" + autoRewardTime +
                ", file=" + file +
                '}';
    }
    public void clear(){
        file = null;
    }
}
