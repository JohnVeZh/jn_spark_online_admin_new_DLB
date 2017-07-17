package com.business.NetworkCourseOrder;


import com.easecom.common.framework.struts.BaseForm;

public class NetworkCourseOrderDetailsActionForm extends BaseForm {
	private static final long serialVersionUID = 1L;
	  // Fields
    private String id;
    private int productType;
    private String productId;
    private String productOrderId;
    private int pcount;
    private String status;
    private double money;

    
    public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId(){
      return id;
    }
    public void setId(String id){
      this.id = id;
    }
    public int getProductType(){
      return productType;
    }
    public void setProductType(int productType){
      this.productType = productType;
    }
    public String getProductId(){
      return productId;
    }
    public void setProductId(String productId){
      this.productId = productId;
    }
    public String getProductOrderId(){
      return productOrderId;
    }
    public void setProductOrderId(String productOrderId){
      this.productOrderId = productOrderId;
    }
}

