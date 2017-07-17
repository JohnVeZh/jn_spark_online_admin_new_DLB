package com.business.ProductOrder; 

import java.math.BigDecimal;

import org.apache.struts.upload.FormFile;

import com.easecom.common.framework.struts.BaseForm;

public class ProductOrderActionForm  extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private int userDel;
    private String zipcode;
    private String createtime;
    private String id;
    private String cityId;
    private String consignee;
    private String orderState;
    private String address;
    private String telephone;
    private int adminDel;
    private String provinceId;
    private double price;
    private String areaId;
    private String remark;
    private String logisticscode;
    private String userId;
    private String orderCode;
    private double postage;
    private int isPostage;
    private BigDecimal payPrice;
    private int fromType;
    private String autoRewardTime;
    private FormFile file;

    public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getAutoRewardTime() {return autoRewardTime;}
    public void setAutoRewardTime(String autoRewardTime) {this.autoRewardTime = autoRewardTime;}
    public BigDecimal getPayPrice() {
        return payPrice;
    }
    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }
    public double getPostage() {
		return postage;
	}
	public void setPostage(double postage) {
		this.postage = postage;
	}
	public int getIsPostage() {
		return isPostage;
	}
	public void setIsPostage(int isPostage) {
		this.isPostage = isPostage;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getUserDel(){
      return userDel;
    }
    public void setUserDel(int userDel){
      this.userDel = userDel;
    }
    public String getZipcode(){
      return zipcode;
    }
    public void setZipcode(String zipcode){
      this.zipcode = zipcode;
    }
    public String getCreatetime(){
      return createtime;
    }
    public void setCreatetime(String createtime){
      this.createtime = createtime;
    }
    public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public String getCityId(){
      return cityId;
    }
    public void setCityId(String cityId){
      this.cityId = cityId;
    }
    public String getConsignee(){
      return consignee;
    }
    public void setConsignee(String consignee){
      this.consignee = consignee;
    }
    public String getOrderState(){
      return orderState;
    }
    public void setOrderState(String orderState){
      this.orderState = orderState;
    }
    public String getAddress(){
      return address;
    }
    public void setAddress(String address){
      this.address = address;
    }
    public String getTelephone(){
      return telephone;
    }
    public void setTelephone(String telephone){
      this.telephone = telephone;
    }
    public int getAdminDel(){
      return adminDel;
    }
    public void setAdminDel(int adminDel){
      this.adminDel = adminDel;
    }
    public String getProvinceId(){
      return provinceId;
    }
    public void setProvinceId(String provinceId){
      this.provinceId = provinceId;
    }
    public double getPrice(){
      return price;
    }
    public void setPrice(double price){
      this.price = price;
    }
    public String getAreaId(){
      return areaId;
    }
    public void setAreaId(String areaId){
      this.areaId = areaId;
    }
    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
    }
    public String getLogisticscode(){
      return logisticscode;
    }
    public void setLogisticscode(String logisticscode){
      this.logisticscode = logisticscode;
    }
    public String getUserId(){
      return userId;
    }
    public void setUserId(String userId){
      this.userId = userId;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public void clear() {
    	file = null;
    }

}

