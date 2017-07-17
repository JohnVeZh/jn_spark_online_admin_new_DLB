package com.business.ProductOrderRefundCheck;

import java.util.Date;

public class ProductOrderRefundCheck extends com.easecom.common.framework.hibernate.BaseModel
		implements java.io.Serializable { 
   /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
  // Fields
    private String productOrderDetailsId;
    private int productStatus;
    private String returnSid;
    private String remark;
    private String createtime;
    private String id;
    private String refundReasonId;
    private String eName;
    private double fee;
    private String orderCode;
    private String eCode;
    private int type;
	private Integer productType;
	private Integer timelyRefund;
	private Double bookPrice;
	private Integer playCount;
	private Date createTime;

    public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String geteCode() {
		return eCode;
	}
	public void seteCode(String eCode) {
		this.eCode = eCode;
	}
	public String getProductOrderDetailsId(){
      return productOrderDetailsId;
    }
    public void setProductOrderDetailsId(String productOrderDetailsId){
      this.productOrderDetailsId = productOrderDetailsId;
    }
    public int getProductStatus(){
      return productStatus;
    }
    public void setProductStatus(int productStatus){
      this.productStatus = productStatus;
    }
    public String getReturnSid(){
      return returnSid;
    }
    public void setReturnSid(String returnSid){
      this.returnSid = returnSid;
    }
    public String getRemark(){
      return remark;
    }
    public void setRemark(String remark){
      this.remark = remark;
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
    public String getRefundReasonId(){
      return refundReasonId;
    }
    public void setRefundReasonId(String refundReasonId){
      this.refundReasonId = refundReasonId;
    }
    public double getFee(){
      return fee;
    }
    public void setFee(double fee){
      this.fee = fee;
    }
    public String getOrderCode(){
      return orderCode;
    }
    public void setOrderCode(String orderCode){
      this.orderCode = orderCode;
    }
    public int getType(){
      return type;
    }
    public void setType(int type){
      this.type = type;
    }

/** default constructor */
	public ProductOrderRefundCheck() {
		super();
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
