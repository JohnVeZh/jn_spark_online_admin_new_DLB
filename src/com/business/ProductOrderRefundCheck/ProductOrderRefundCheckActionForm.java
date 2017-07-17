package com.business.ProductOrderRefundCheck;

import com.easecom.common.framework.struts.BaseForm;

public class ProductOrderRefundCheckActionForm extends BaseForm {
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
	public String getProductOrderDetailsId() {
		return productOrderDetailsId;
	}
	public void setProductOrderDetailsId(String productOrderDetailsId) {
		this.productOrderDetailsId = productOrderDetailsId;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public String getReturnSid() {
		return returnSid;
	}
	public void setReturnSid(String returnSid) {
		this.returnSid = returnSid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefundReasonId() {
		return refundReasonId;
	}
	public void setRefundReasonId(String refundReasonId) {
		this.refundReasonId = refundReasonId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String geteCode() {
		return eCode;
	}
	public void seteCode(String eCode) {
		this.eCode = eCode;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}

