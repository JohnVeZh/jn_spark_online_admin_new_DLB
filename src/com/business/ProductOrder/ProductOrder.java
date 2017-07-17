package com.business.ProductOrder;

import java.math.BigDecimal;

public class ProductOrder extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private int userDel; //用户是否删除
    private String zipcode; //邮编
    private String createtime; //创建时间
    private String id; //唯一标识
    private String cityId;  //市
    private String consignee; //收货人
    private String orderState; //订单状态
    private String address; //收货人详细地址
    private String telephone; //收货人电话
    private int adminDel; //管理员删除
    private String provinceId;  //省份
    private double price; //商品销售价
    private String areaId; //区县
    private String remark; //备注
    private String logisticscode; //物流单号
    private String userId; //用户id
    private String orderCode; //订单号
    private double postage;
    private int isPostage;
    private int type;
    private String districtCn;
    private int payType;
    private int fromType;
    private BigDecimal payPrice;
    private String autoRewardTime;

    public String getAutoRewardTime() {return autoRewardTime;}
    public void setAutoRewardTime(String autoRewardTime) {this.autoRewardTime = autoRewardTime;}
    public BigDecimal getPayPrice() {
        return payPrice;
    }
    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }
    public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDistrictCn() {
		return districtCn;
	}
	public void setDistrictCn(String districtCn) {
		this.districtCn = districtCn;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
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

    /** default constructor */
	public ProductOrder() {
	}
public ProductOrder(int userDel, String zipcode, String createtime, String id,
		String cityId, String consignee, String orderState, String address,
		String telephone, int adminDel, String provinceId, double price,
		String areaId, String remark, String logisticscode, String userId,
		String orderCode, double postage, int isPostage,BigDecimal payPrice) {
	super();
	this.userDel = userDel;
	this.zipcode = zipcode;
	this.createtime = createtime;
	this.id = id;
	this.cityId = cityId;
	this.consignee = consignee;
	this.orderState = orderState;
	this.address = address;
	this.telephone = telephone;
	this.adminDel = adminDel;
	this.provinceId = provinceId;
	this.price = price;
	this.areaId = areaId;
	this.remark = remark;
	this.logisticscode = logisticscode;
	this.userId = userId;
	this.orderCode = orderCode;
	this.postage = postage;
	this.isPostage = isPostage;
    this.payPrice = payPrice;
}

}
